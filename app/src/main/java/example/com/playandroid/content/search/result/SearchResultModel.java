package example.com.playandroid.content.search.result;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;

import org.jetbrains.annotations.NotNull;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.adapter.SearchResultAdapter;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.content.search.SearchActivity;
import example.com.playandroid.databinding.FragmentSearchResultBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import example.com.playandroid.util.ArouterUtil;
import example.com.playandroid.util.DatabaseHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/18 17:00
 */
public class SearchResultModel extends BaseFragmentModel<SearchActivity, SearchResultFragment, FragmentSearchResultBinding> {
    private String query;
    private DatabaseHelper db;
    private SearchResultAdapter searchResultAdapter;
    private int curPage = 0;

    public SearchResultModel(SearchActivity activity, SearchResultFragment fragment) {
        super(activity, fragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = new DatabaseHelper();
        query = getFragment().getArguments().getString("QUERY");
        db.saveSearchHistory(query);

        //初始化adapter 以及 RecyclerView 包括LinearLayoutManager以及加入下划线
        initRvAndAdapter();

    }

    private void initRvAndAdapter() {
        RecyclerView recyclerView = getBinding().recyclerView;
        searchResultAdapter = new SearchResultAdapter(R.layout.item_search_article, getActivity());
        searchResultAdapter.setEmptyView(R.layout.no_data_view, (ViewGroup) getBinding().getRoot());
        searchResultAdapter.setOnLoadMoreListener(this::onLoadMore, recyclerView);
        searchResultAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            ArticleEntity entity = (ArticleEntity) adapter.getItem(position);
            bundle.putString(Constant.link, entity.getLink());
            bundle.putInt(Constant.article_id, entity.getId());
            bundle.putString(Constant.article_title, entity.getTitle());
            ArouterUtil.navigation(Constant.ActivityPath.WebViewActivity,bundle);
        });
        recyclerView.setAdapter(searchResultAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.list_divider);
        itemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(itemDecoration);

        addDisposable(search(0, true));
    }
    @NotNull
    private Disposable search(int page, boolean isFirst) {
        return App.api.searchArticles(page, query)
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pageEntity -> {
                    if (isFirst) {
                        searchResultAdapter.setNewData(pageEntity.getArticleEntities());
                    } else {
                        searchResultAdapter.addData(pageEntity.getArticleEntities());
                        if (curPage * pageEntity.getSize() >= pageEntity.getTotal()) {
                            //数据全部加载完毕
                            searchResultAdapter.loadMoreEnd();
                        } else {
                            searchResultAdapter.loadMoreComplete();
                        }
                    }
                    curPage = pageEntity.getCurPage();
                }, throwable -> {
                    throwable.printStackTrace();
                    ToastUtils.showLong("获取搜索列表失败");
                    if (isFirst) {
                        searchResultAdapter.loadMoreFail();
                    }
                });
    }

    private void onLoadMore() {
        addDisposable(search(curPage + 1, false));
    }
}
