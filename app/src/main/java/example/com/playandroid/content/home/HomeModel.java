package example.com.playandroid.content.home;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.network.transform.RestfulTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 22:10
 */
public class HomeModel extends BaseFragmentModel<MainActivity, HomeFragment> {
    private List<BannerEntity> mBannerEntities = new ArrayList<>();
    private List<ArticleEntity> articles;



    public HomeModel(MainActivity activity, HomeFragment fragment) {
        super(activity, fragment);
    }

    @Override
    public void onFragmentCreate() {
        super.onFragmentCreate();
        addDisposable(App.api.getBannerEntity()
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::doOnNext, a->{a.printStackTrace();ToastUtils.showLong("获取数据失败");}));

        articles = new ArrayList<>();
        addDisposable(App.api.getFeedArticleList(0)
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(page->{
                    RecyclerView recyclerView = getFragment().getBinding().recyclerView;
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(new ArticleBindingAdapter(page.getArticleEntities(),recyclerView.getContext()));

                   /* List<TestEntity> list = new ArrayList<>();
                    for (int i = 0; i < 200; i++) {
                        TestEntity testEntity = new TestEntity();
                        testEntity.setContent("hahahaha "+i);
                        list.add(testEntity);
                    }
                    RecyclerView recyclerView = getFragment().getBinding().recyclerView;
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(new TestBindingAdapter(list,recyclerView.getContext()));*/
                    //recyclerView.setOnBottomCallback(() -> ToastUtils.showLong("到底啦 老哥"));
                },Throwable::printStackTrace)
        );
        /*for (int i = 0; i < 200; i++) {
            ArticleEntity testEntity = new ArticleEntity();
            //testEntity.setContent("hahahaha "+i);
            articles.add(testEntity);
        }*/
       /*RecyclerView recyclerView = getFragment().getBinding().recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ArticleBindingAdapter(articles, recyclerView.getContext()));*/
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
