package example.com.playandroid.content.home;


import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseFragmentModel;
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
        recyclerView.setAdapter(new ArticleBindingAdapter(page.getArticleEntities(), recyclerView.getContext()));
    }

    private void refreshZero(PageEntity page) {
        mPageEntity = page;
        ArticleBindingAdapter adapter = (ArticleBindingAdapter) (getBinding().recyclerView.getAdapter());
        SmartRefreshLayout refreshLayout = getBinding().refreshLayout;
        List newData = new ArrayList<ArticleEntity>();
        addDisposable(
            Observable.fromIterable(adapter.getData().subList(0, page.getSize()))
                    .doOnNext(article -> {
                        int i = 0;
                        for (ArticleEntity articleEntity : page.getArticleEntities()) {
                           /* if (articleEntity.equals(article)) page.getArticleEntities().remove(articleEntity);
                            else newData.add(article);*/
                            if (!articleEntity.equals(article)) i++;
                        }
                        if (i == page.getArticleEntities().size()) newData.add(article);
                    })
                    .toList().toObservable()
                    .subscribe(list -> {
                        adapter.addList(newData);
                        refreshLayout.finishRefresh(1000, true);
                    }, (throwable -> {
                        refreshLayout.finishRefresh(1000, false);
                        throwable.printStackTrace();
                    }))
        );
    }

    private void loadSuccess(PageEntity pageEntity) {
        mPageEntity = pageEntity;
        ((ArticleBindingAdapter) getBinding().recyclerView.getAdapter()).addList(pageEntity.getArticleEntities());
        getBinding().refreshLayout.finishLoadMore(1000, true, false);
    }

    private void loadFail(Throwable throwable) {
        getBinding().refreshLayout.finishLoadMore(false);
        throwable.printStackTrace();
    }

    private void doOnNext(List<BannerEntity> list) {
        List<String> titleList = new ArrayList<>();
        List imagesList = new ArrayList<>();
        Banner banner = getFragment().getBinding().banner;
        mBannerEntities = list;
        for (BannerEntity bannerEntity : list) {
            titleList.add(bannerEntity.getTitle());
            imagesList.add(bannerEntity.getImagePath());
        }
        banner.setImages(imagesList);
        banner.setBannerTitles(titleList);
        banner.start();
    }


    public void OnBannerClick(int position) {
        ToastUtils.showLong(mBannerEntities.get(position).getUrl());
    }

}
