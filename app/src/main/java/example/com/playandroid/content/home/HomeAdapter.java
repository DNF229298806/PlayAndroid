package example.com.playandroid.content.home;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.base.test.BindingAdapter;
import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.databinding.HolderBannerBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import example.com.playandroid.util.GlideImageLoader;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/28 21:17
 */
public class HomeAdapter extends BindingAdapter {
    @Override
    public BindingAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        if (binding instanceof HolderBannerBinding) {
            Banner banner = ((HolderBannerBinding) binding).banner;
            ((BaseActivity) (App.getCurrentActivity())).getModel().addDisposable(
                App.api.getBannerEntity()
                        .compose(new RestfulTransformer<>())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(bannerEntities -> {
                            List<String> titleList = new ArrayList<>();
                            List imagesList = new ArrayList<>();
                            for (BannerEntity bannerEntity : bannerEntities) {
                                titleList.add(bannerEntity.getTitle());
                                imagesList.add(bannerEntity.getImagePath());
                            }
                            banner.setImageLoader(new GlideImageLoader());
                            banner.setBannerAnimation(Transformer.Default);
                            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                            banner.setImages(imagesList);
                            banner.setBannerTitles(titleList);
                            banner.start();
                        }, a -> {
                            a.printStackTrace();
                            ToastUtils.showLong("获取数据失败");
                        })
            );
        }
        return new BindingHolder(binding);
    }

}
