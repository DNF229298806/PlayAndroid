package example.com.playandroid.util;

import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.util.Patterns;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.File;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/27 23:26
 */
public class DataBindingAdapter {

    //BottomNavigationView
    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelected(BottomNavigationView view, OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    //Banner
    @BindingAdapter("onBannerClick")
    public static void setOnBannerClick(Banner view, OnBannerListener listener) {
        view.setOnBannerListener(listener);
    }

    //ImageView
    @BindingAdapter("generalSrc")
    public static void setFilePath(ImageView view, String path) {
        if (Patterns.WEB_URL.matcher(path).matches()) {
            Glide.with(view.getContext()).load(path).into(view);
        } else {
            Glide.with(view.getContext()).load(new File(path)).into(view);
        }
    }

    //SmartRefreshLayout
    @BindingAdapter("onLoadMoreListener")
    public static void setOnLoadMoreListener(SmartRefreshLayout layout, OnLoadMoreListener listener) {
        layout.setOnLoadMoreListener(listener);
    }

    //SmartRefreshLayout
    @BindingAdapter("onRefreshListener")
    public static void setOnRefreshListener(SmartRefreshLayout layout, OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
    }

    //ToolBar 但是要求5.0以上
    @BindingAdapter("navigationOnClickListener")
    public static void setNavigationOnClickListener(Toolbar toolbar, OnClickListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationOnClickListener(listener);
        }
    }

}
