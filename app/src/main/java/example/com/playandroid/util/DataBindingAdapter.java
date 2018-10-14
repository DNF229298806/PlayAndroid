package example.com.playandroid.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

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


    /*@BindingAdapter({"OnNavigationItemSelectedListener"})
    public static void setOnNavigationItemSelectedListener(BottomNavigationView view) {
        view.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.it_home:
                    ToastUtils.showLong("home");
                    break;
                case R.id.it_project:
                    ToastUtils.showLong("it_project");
                    break;
                case R.id.it_system:
                    ToastUtils.showLong("it_system");
                    break;
                case R.id.it_nav:
                    ToastUtils.showLong("it_nav");
                    break;
                default:
                    break;
            }
            return false;
        });
    }*/
}
