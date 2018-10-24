package example.com.playandroid.content.main;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.content.home.HomeFragment;
import example.com.playandroid.content.navigation.NavigationFragment;
import example.com.playandroid.content.project.ProjectFragment;
import example.com.playandroid.content.system.SystemFragment;
import example.com.playandroid.databinding.ActivityMainBinding;

import static example.com.playandroid.util.Constant.FragmentType.HOME;
import static example.com.playandroid.util.Constant.FragmentType.NAVIGATION;
import static example.com.playandroid.util.Constant.FragmentType.PROJECT;
import static example.com.playandroid.util.Constant.FragmentType.SYSTEM;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/27 21:49
 */
public class MainModel extends BaseModel<MainActivity,ActivityMainBinding> {

    public MainModel(MainActivity activity) {
        super(activity);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        MainActivity activity = getActivity();
        HomeFragment homeFragment = activity.getHomeFragment();
        NavigationFragment navigationFragment = activity.getNavigationFragment();
        ProjectFragment projectFragment = activity.getProjectFragment();
        SystemFragment systemFragment = activity.getSystemFragment();

        switch (item.getItemId()) {
            case R.id.it_home:
                activity.initFragment(homeFragment,HOME);
                ToastUtils.showLong("DataBindingAdapter home");
                return true;
            case R.id.it_project:
                activity.initFragment(projectFragment,PROJECT);
                ToastUtils.showLong("DataBindingAdapter it_project");
                return true;
            case R.id.it_system:
                activity.initFragment(systemFragment,SYSTEM);
                ToastUtils.showLong("DataBindingAdapter it_system");
                return true;
            case R.id.it_nav:
                activity.initFragment(navigationFragment,NAVIGATION);
                ToastUtils.showLong("DataBindingAdapter it_nav");
                return true;
            default:
                break;
        }
        return false;
    }

}
