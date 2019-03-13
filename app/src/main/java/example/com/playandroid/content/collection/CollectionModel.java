package example.com.playandroid.content.collection;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.databinding.ActivityCollectionBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Richard_Y_Wang
 * @des 2019/1/27 14:58
 */
public class CollectionModel extends BaseModel<CollectionActivity, ActivityCollectionBinding> {
    private CollectionAdapter adapter;

    public CollectionModel(CollectionActivity activity) {
        super(activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RecyclerView recyclerView = getBinding().recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CollectionAdapter(R.layout.holder_collection_item);
        recyclerView.setAdapter(adapter);
        firstLoad();
    }

    private void firstLoad() {
        addDisposable(
                App.api.getCollectList(0)
                        .compose(new RestfulTransformer<>())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(th -> {
                            adapter.setNewData(th.getDatas());
                            ToastUtils.showLong("请求成功");
                        }, throwable -> {
                            adapter.setEmptyView(R.layout.error_view, (ViewGroup) getBinding().recyclerView.getParent());
                            ToastUtils.showLong(throwable.getMessage());
                            throwable.printStackTrace();
                        }));
    }

    /**
     * 下拉加载更多
     *
     * @param refreshlayout smart控件
     */
    public void onLoadMore(@NonNull RefreshLayout refreshlayout) {

    }

    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    public void onBackClick(View view) {
        getActivity().finish();
    }
}
