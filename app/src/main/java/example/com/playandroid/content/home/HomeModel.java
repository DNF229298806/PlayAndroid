package example.com.playandroid.content.home;


import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.ViewReplacer;
import com.ethanhua.skeleton.ViewSkeletonScreen;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.base.Mult;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.content.home.net.PageEntity;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentHomeBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 22:10
 */
public class HomeModel extends BaseFragmentModel<MainActivity, HomeFragment, FragmentHomeBinding> {
    private List<BannerEntity> mBannerEntities;
    private PageEntity mPageEntity;
    private HomeAdapter mAdapter;
    RecyclerViewSkeletonScreen loadingScreen;
    ViewSkeletonScreen loadingViewScreen;
    ViewReplacer mViewReplacer;
    public HomeModel(MainActivity activity, HomeFragment fragment) {
        super(activity, fragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBannerEntities = new ArrayList<>();
        mPageEntity = new PageEntity();
        RecyclerView recyclerView = getBinding().recyclerView;
        mAdapter= new HomeAdapter();
        mViewReplacer = new ViewReplacer(getBinding().root);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        Timber.i("setAdapter=" + recyclerView.hashCode());
        //绑定recyclerView
       /* loadingScreen = Skeleton.bind(recyclerView)
                .adapter(mAdapter)
                .load(R.layout.holder_article_item)
                .show();*/
       //绑定View
        loadingViewScreen = Skeleton.bind(getBinding().refreshLayout)
                .load(R.layout.loading_home)
                .shimmer(true) //要不要光 默认开启
                .show();
        addDisposable(App.api.getBannerEntity()
                .compose(new RestfulTransformer<>())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::doOnNext, a -> {
                    a.printStackTrace();
                    ToastUtils.showLong("获取数据失败");
                }));
        refreshFirstPage(this::doOnFirstLoading);
    }


    /**
     * 下拉加载更多
     *
     * @param refreshlayout smart控件
     */
    public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
        if (!mPageEntity.isOver())
            addDisposable(App.api.getFeedArticleList(mPageEntity.getCurPage())
                    .compose(new RestfulTransformer<>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::loadSuccess, this::loadFail));
    }

    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshFirstPage(this::refreshZero);
    }


    private void refreshFirstPage(Consumer<PageEntity> onNext) {
        addDisposable(App.api.getFeedArticleList(0)
                .compose(new RestfulTransformer<>())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, throwable -> {
                    throwable.printStackTrace();
                    //mViewReplacer.replace();
                })
        );
    }

    private void doOnFirstLoading(PageEntity page) {
        mPageEntity = page;
       /* RecyclerView recyclerView = getBinding().recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);*/
        List<Mult> list = new ArrayList<>();
        //把banner的数据传递进去
        list.add(new BannerLayoutEntity(mBannerEntities));
        list.addAll(page.getArticleEntities());
        Timber.i("refreshList=" + getBinding().recyclerView.hashCode());
        mAdapter.refreshList(list);
        //mAdapter.addList(list);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void refreshZero(PageEntity page) {
        //查重代码还是有问题的
        mPageEntity = page;
        SmartRefreshLayout refreshLayout = getBinding().refreshLayout;
        refreshLayout.finishRefresh(1000, true);
        ToastUtils.showShort("暂无数据更新");

    }

    private void loadSuccess(PageEntity pageEntity) {
        mPageEntity = pageEntity;
        List<ArticleEntity> articleEntities = pageEntity.getArticleEntities();
        List<Mult> list = new ArrayList<>(articleEntities);
        mAdapter.addList(list);
        getBinding().refreshLayout.finishLoadMore(1000, true, false);
    }

    private void loadFail(Throwable throwable) {
        getBinding().refreshLayout.finishLoadMore(false);
        throwable.printStackTrace();
    }

    private void doOnNext(List<BannerEntity> list) {
        //loadingScreen.hide();
        loadingViewScreen.hide();
        mBannerEntities = list;
    }

}
