package example.com.playandroid.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.base.Mult;
import example.com.playandroid.base.test.BindingAdapter;
import example.com.playandroid.content.home.RewardDialog;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.databinding.HolderArticleItemBinding;
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
        } else if (binding instanceof HolderArticleItemBinding) {
            ImageView ivGood = ((HolderArticleItemBinding) binding).ivGood;
            TextView tvGoodCount = ((HolderArticleItemBinding) binding).tvGoodCount;
            ((HolderArticleItemBinding) binding).ivReward.setOnClickListener(v -> {
                //todo 请输入打赏金额
                RewardDialog dialog = new RewardDialog(ivGood.getContext());
                dialog.show();
            });
            ivGood.setOnClickListener(v -> {
                onGoodClick(binding, ivGood, tvGoodCount);
                //entity.setGoodCount(entity.isGood() ? ++goodCount : --goodCount);
            });
            tvGoodCount.setOnClickListener(v -> onGoodClick(binding, ivGood, tvGoodCount));
        }
        return new BindingHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        List<Mult> list = getList();
        if (position != 0 &&  list.get(position) instanceof ArticleEntity) {
            ArticleEntity entity = (ArticleEntity) list.get(position);
            HolderArticleItemBinding binding = (HolderArticleItemBinding) holder.getBinding();
            if (entity.isGood()) {
                Glide.with(binding.getRoot()).load(R.drawable.ic_good_on).into(binding.ivGood);
            } else {
                Glide.with(binding.getRoot()).load(R.drawable.ic_good_off).into(binding.ivGood);
            }
        }
    }

    private void onGoodClick(ViewDataBinding binding, ImageView ivGood, TextView tvGoodCount) {
        ArticleEntity entity = ((HolderArticleItemBinding) binding).getEntity();
        entity.setGood(!entity.isGood());
        int goodCount = entity.getGoodCount();
        if (entity.isGood()) {
            Glide.with(binding.getRoot()).load(R.drawable.ic_good_on).into(ivGood);
            goodCount++;
        }else{
            Glide.with(binding.getRoot()).load(R.drawable.ic_good_off).into(ivGood);
            goodCount--;
        }
        ivGood.startAnimation(AnimationUtils.loadAnimation(ivGood.getContext(),R.anim.good));
        entity.setGoodCount(goodCount);
        tvGoodCount.setText(String.valueOf(goodCount));
    }

}
