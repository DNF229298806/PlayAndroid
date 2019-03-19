package example.com.playandroid.content.search.suggest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.adapter.HotKeyAdapter;
import example.com.playandroid.adapter.SearchHistoryAdapter;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.content.search.SearchActivity;
import example.com.playandroid.content.search.result.SearchResultFragment;
import example.com.playandroid.databinding.FragmentSearchSuggestBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import example.com.playandroid.util.DatabaseHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/18 16:07
 */
public class SearchSuggestModel extends BaseFragmentModel<SearchActivity, SearchSuggestFragment, FragmentSearchSuggestBinding> {
    private HotKeyAdapter hotKeyAdapter;
    private SearchHistoryAdapter searchHistoryAdapter;

    public SearchSuggestModel(SearchActivity activity, SearchSuggestFragment fragment) {
        super(activity, fragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RecyclerView hotSearchList = getBinding().hotSearchList;
        RecyclerView searchHistoryList = getBinding().searchHistoryList;

        hotSearchList.setLayoutManager(new FlexboxLayoutManager(getActivity()));
        hotKeyAdapter = new HotKeyAdapter(R.layout.item_textview_tag);
        hotSearchList.setAdapter(hotKeyAdapter);
        //回去加点击事件
        hotKeyAdapter.setOnItemClickListener(this::hotKeyClick);

        searchHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchHistoryAdapter = new SearchHistoryAdapter(R.layout.item_search_history);
        searchHistoryAdapter.setOnItemChildClickListener(SearchSuggestModel::onChildDeleteClick);

        searchHistoryList.setAdapter(searchHistoryAdapter);

        //网络请求热词 并显示
        addDisposable(App.api.getHotKeyEntity()
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hotKeyEntities -> hotKeyAdapter.setNewData(hotKeyEntities),
                        throwable -> {
                            throwable.printStackTrace();
                            ToastUtils.showLong("热词获取错误");
                        }));

        //去数据库拿数据显示搜索记录
        DatabaseHelper db = new DatabaseHelper();
        searchHistoryAdapter.setNewData(db.querySearchHistory());

        getBinding().clearHistory.setOnClickListener(v -> {
            db.deleteSearchHistoryAll();
            searchHistoryAdapter.setNewData(new ArrayList<>());
        });
    }

    private void hotKeyClick(BaseQuickAdapter adapter, View view, int position) {
        //点击以后出现跳转
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        SearchResultFragment.newInstance(((HotKeyEntity) adapter.getItem(position)).getName()))
                .commit();
    }

    private static void onChildDeleteClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.delete) {
            DatabaseHelper db = new DatabaseHelper();
            SearchHistoryEntity entity = (SearchHistoryEntity) adapter.getItem(position);
            db.deleteSearchHistory(entity.getKeyword());
            adapter.remove(position);
        }
    }
}
