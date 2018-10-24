package example.com.playandroid.content.home;


import android.support.v7.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.base.SuperRecyclerView;
import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.content.home.net.PageEntity;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentHomeBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 22:10
 */
public class HomeModel extends BaseFragmentModel<MainActivity, HomeFragment,FragmentHomeBinding> {
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
        addDisposable(App.api.getFeedArticleList(0)
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::doOnFirstLoading, Throwable::printStackTrace)
        );
    }


    private void doOnFirstLoading(PageEntity page) {
        mPageEntity = page;
        SuperRecyclerView recyclerView = getBinding().recyclerView;
        recyclerView.setOnBottomCallback(this::doOnBottom);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ArticleBindingAdapter(page.getArticleEntities(), recyclerView.getContext()));
    }

    private void doOnBottom() {
        if (!mPageEntity.isOver())
            addDisposable(App.api.getFeedArticleList(mPageEntity.getCurPage())
                    .compose(new RestfulTransformer<>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::more, Throwable::printStackTrace));
    }


    private void more(PageEntity pageEntity) {
        mPageEntity = pageEntity;
        ((ArticleBindingAdapter) getBinding().recyclerView.getAdapter()).appendItems(pageEntity.getArticleEntities());
        ToastUtils.showShort("加载成功");
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
