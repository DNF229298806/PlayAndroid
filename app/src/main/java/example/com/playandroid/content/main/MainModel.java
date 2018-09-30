package example.com.playandroid.content.main;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.base.BaseModel;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/27 21:49
 */
public class MainModel extends BaseModel {
    private MainActivity mActivity;
    private Resources mResources;
    private BottomNavigationView bnv;

    public MainModel(BaseActivity activity) {
        super(activity);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it_home:
                ToastUtils.showLong("DataBindingAdapter home");
                return true;
            case R.id.it_project:
                ToastUtils.showLong("DataBindingAdapter it_project");
                return true;
            case R.id.it_system:
                ToastUtils.showLong("DataBindingAdapter it_system");
                return true;
            case R.id.it_nav:
                ToastUtils.showLong("DataBindingAdapter it_nav");
                return true;
            default:
                break;
        }
        return false;
    }

}
