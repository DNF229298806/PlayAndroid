package example.com.playandroid.content.home;

import android.databinding.ViewDataBinding;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseEntity;

/**
 * @author admin
 * @des 2018/10/26
 */
public class BannerLayoutEntity extends BaseEntity {
    @Override
    public int getViewType() {
        return R.layout.holder_banner;
    }

    @Override
    public ViewDataBinding getDataBinding() {
        return null;
    }

    @Override
    public void setDataBinding(ViewDataBinding viewDataBinding) {

    }

   /* public void OnBannerClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.link,mBannerEntities.get(position).getUrl());
        ArouterUtil.navigation(Constant.ActivityPath.WebViewActivity,bundle);
    }*/
}
