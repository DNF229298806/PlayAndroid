package example.com.playandroid.content.project.tabpage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.adapter.SearchResultAdapter;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.content.project.ProjectFragment;
import example.com.playandroid.network.transform.RestfulTransformer;
import example.com.playandroid.util.ArouterUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/20 10:25
 */
public class ProjectTabPageFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private int cid = 0;
    private int curPage = 0;
    private SearchResultAdapter adapter;
    private ListCompositeDisposable list = new ListCompositeDisposable();

    public static ProjectTabPageFragment newInstance(int cid) {
        Bundle args = new Bundle();
        args.putInt("CID", cid);
        ProjectTabPageFragment fragment = new ProjectTabPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //所以搞了这么久 问题是出在网络请求不能被disposable 因为本来的写法是绑定在这个fragment上 如果网络请求还没有走完就被
    //disposable的话 界面上就自然没有数据了
    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isVisible()) {//视图可见并且控件准备好了，每次都会调用
            if (adapter.getData().size() == 0) {//如果数据为空了，则需要重新联网请求

            }
        }

    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_tab_page, container, false);
        unbinder = ButterKnife.bind(this, view);
        //设置当前页数
        curPage = 0;
        //设置adapter
        cid = getArguments().getInt("CID", 0);
        adapter = new SearchResultAdapter(R.layout.item_search_article, getActivity());
        adapter.setEmptyView(R.layout.no_data_view, (ViewGroup) view);
        adapter.setOnLoadMoreListener(this::onLoadMore, recyclerView);
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            Bundle bundle = new Bundle();
            ArticleEntity entity = (ArticleEntity) adapter.getItem(position);
            bundle.putString(Constant.link, entity.getLink());
            bundle.putInt(Constant.article_id, entity.getId());
            bundle.putString(Constant.article_title, entity.getTitle());
            ArouterUtil.navigation(Constant.ActivityPath.WebViewActivity, bundle);
        });
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.list_divider);
        itemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(itemDecoration);
        ((ProjectFragment)getParentFragment()).getModel().addDisposable(loadSysArticle(curPage, cid, true));
        return view;
    }

    @NotNull
    private Disposable loadSysArticle(int page, int cid, boolean isFirst) {
        return App.api.getProjectArticles(page, cid)
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pageEntity -> {
                    ToastUtils.showLong("获取搜索列表成功");
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
                    ToastUtils.showLong("获取搜索列表失败");
                    if (isFirst) {
                        adapter.loadMoreFail();
                    }
                });
    }

    private void onLoadMore() {
        ((ProjectFragment)getParentFragment()).getModel().addDisposable(loadSysArticle(curPage + 1, cid, false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.d("当场去世！！！！！！！！！！！");
        unbinder.unbind();
    }
}
