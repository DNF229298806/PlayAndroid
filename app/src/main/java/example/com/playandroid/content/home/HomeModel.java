package example.com.playandroid.content.home;


import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.base.Mult;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.content.home.net.PageEntity;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentHomeBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 22:10
 */
public class HomeModel extends BaseFragmentModel<MainActivity, HomeFragment, FragmentHomeBinding> {
    private List<BannerEntity> mBannerEntities = new ArrayList<>();
    private PageEntity mPageEntity = new PageEntity();
    private HomeAdapter mAdapter = new HomeAdapter();

    public HomeModel(MainActivity activity, HomeFragment fragment) {
        super(activity, fragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        addDisposable(App.api.getBannerEntity()
                .compose(new RestfulTransformer<>())
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, Throwable::printStackTrace)
        );
    }

    private void doOnFirstLoading(PageEntity page) {
        mPageEntity = page;
        RecyclerView recyclerView = getBinding().recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        List<Mult> list = new ArrayList<>();
        //把banner的数据传递进去
        list.add(new BannerLayoutEntity(mBannerEntities));
        list.addAll(page.getArticleEntities());
        mAdapter.addList(list);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void refreshZero(PageEntity page) {
        //查重代码还是有问题的
        mPageEntity = page;
        SmartRefreshLayout refreshLayout = getBinding().refreshLayout;
        int min = mAdapter.getItemCount() < page.getCurPage() ? mAdapter.getItemCount() : page.getCurPage();
        addDisposable(
            Observable.fromIterable(new ArrayList<>(mAdapter.getList().subList(1, min)))
                    .filter(article->{
                        int i = 0;
                        for (ArticleEntity articleEntity : page.getArticleEntities()) {
                            if (!articleEntity.equals(article)) i++;
                        }
                        return i == page.getArticleEntities().size();
                    })
                    .toList().toObservable()
                    .subscribe(list -> {
                        mAdapter.addList(1,list);
                        refreshLayout.finishRefresh(1000, true);
                    }, (throwable -> {
                        refreshLayout.finishRefresh(1000, false);
                        throwable.printStackTrace();
                    }))
        );
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
        mBannerEntities = list;
    }

}
