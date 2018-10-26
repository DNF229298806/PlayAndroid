package example.com.playandroid.content.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseFragment;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentHomeBinding;
import example.com.playandroid.util.GlideImageLoader;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/27 22:08
 */
public class HomeFragment extends BaseFragment<MainActivity,HomeModel,FragmentHomeBinding> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //初始化model 并与fragment进行绑定
        setModel(new HomeModel((MainActivity) getActivity(),this));
        initBanner();
        return getBinding().getRoot();
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    public void initBanner() {
        Banner banner = getBinding().banner;
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
