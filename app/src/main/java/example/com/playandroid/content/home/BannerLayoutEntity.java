package example.com.playandroid.content.home;

import android.os.Bundle;

import java.util.List;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseEntity;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.databinding.HolderBannerBinding;
import example.com.playandroid.util.ArouterUtil;

/**
 * @author admin
 * @des 2018/10/26
 */
public class BannerLayoutEntity extends BaseEntity<HolderBannerBinding> {

    private List<BannerEntity> list;
    /*在这边写一个list 用构造方法传进来*/

    public BannerLayoutEntity(List<BannerEntity> list) {
        this.list = list;
    }

    @Override
    public int getViewType() {
        return R.layout.holder_banner;
    }

    @Override
    public HolderBannerBinding getDataBinding() {
        return null;
    }

    @Override
    public void setDataBinding(HolderBannerBinding holderBannerBinding) {
        binding = holderBannerBinding;
        holderBannerBinding.setEntity(this);
    }

    public void OnBannerClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.link,list.get(position).getUrl());
        ArouterUtil.navigation(Constant.ActivityPath.WebViewActivity,bundle);
    }
}
