package example.com.playandroid.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.util.Patterns;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.File;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/27 23:26
 */
public class DataBindingAdapter {

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelected(BottomNavigationView view, OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter("onBannerClick")
    public static void setOnBannerClick(Banner view, OnBannerListener listener) {
        view.setOnBannerListener(listener);
    }

    @BindingAdapter("generalSrc")
    public static void setFilePath(ImageView view, String path) {
        if (Patterns.WEB_URL.matcher(path).matches()) {
            Glide.with(view.getContext()).load(path).into(view);
        } else {
            Glide.with(view.getContext()).load(new File(path)).into(view);
        }
    }

}
