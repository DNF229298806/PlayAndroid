package example.com.playandroid.content.system;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.adapter.SearchResultAdapter;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.network.transform.RestfulTransformer;
import example.com.playandroid.util.ArouterUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/19 16:50
 */
@Route(path = Constant.ActivityPath.SystemArticleActivity)
public class SystemArticleActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SearchResultAdapter adapter;
    public ListCompositeDisposable list = new ListCompositeDisposable();
    private int curPage = 0;
    private int id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra(Constant.id, 0);
        String class_title = getIntent().getStringExtra(Constant.class_title);
        setContentView(R.layout.activity_system_article);
        ButterKnife.bind(this);
        //设置toolbar Title
        toolbar.setTitle(class_title);
        //toolbar上面的返回键的监听
        toolbar.setNavigationOnClickListener(v -> finish());
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new SearchResultAdapter(R.layout.item_search_article, this);
        //加载更多
        adapter.setOnLoadMoreListener(this::onLoadMore, recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            ArticleEntity entity = (ArticleEntity) adapter1.getItem(position);
            bundle.putString(Constant.link, entity.getLink());
            bundle.putInt(Constant.article_id, entity.getId());
            bundle.putString(Constant.article_title, entity.getTitle());
            ArouterUtil.navigation(Constant.ActivityPath.WebViewActivity,bundle);
        });
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.list_divider);
        itemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(itemDecoration);
        //开始加载文章
        loadArticles(curPage, id, true);
    }

    private void onLoadMore() {
        addDisposable(loadArticles(curPage + 1, id, false));
    }


    public void addDisposable(Disposable disposable) {
        list.add(disposable);
    }

    @NotNull
    private Disposable loadArticles(int page, int id, boolean isFirst) {
        return App.api.getHierarchyArticles(page, id)
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pageEntity -> {
                    if (isFirst) {
                        adapter.setNewData(pageEntity.getArticleEntities());
                    } else {
                        adapter.addData(pageEntity.getArticleEntities());
                        if (curPage * pageEntity.getSize() >= pageEntity.getTotal()) {
                            //数据全部加载完毕
                            adapter.loadMoreEnd();
                        } else {
                            adapter.loadMoreComplete();
                        }
                    }
                    curPage = pageEntity.getCurPage();
                }, throwable -> {
                    throwable.printStackTrace();
                    ToastUtils.showLong("获取列表失败");
                    if (isFirst) {
                        adapter.loadMoreFail();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list != null) {
            list.dispose();
        }
    }
}
