package example.com.playandroid.content.main;

import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.content.home.HomeFragment;
import example.com.playandroid.content.navigation.NavigationFragment;
import example.com.playandroid.content.project.ProjectFragment;
import example.com.playandroid.content.system.SystemFragment;
import example.com.playandroid.databinding.ActivityMainBinding;

import static example.com.playandroid.constant.Constant.FragmentType.HOME;
import static example.com.playandroid.constant.Constant.FragmentType.NAVIGATION;
import static example.com.playandroid.constant.Constant.FragmentType.PROJECT;
import static example.com.playandroid.constant.Constant.FragmentType.SYSTEM;

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

    public void navigationClick(View view) {
        ToastUtils.showShort("出现菜单栏");
        getBinding().drawableLayout.openDrawer(getBinding().navigationView);
    }

    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_collection:
                ToastUtils.showShort("这是收藏");
                break;
            case R.id.menu_item_navigation:
                ToastUtils.showShort("这是导航");
                break;
            case R.id.menu_item_open_apis:
                ToastUtils.showShort("这是开源Api");
                break;
            case R.id.menu_item_friend_link:
                ToastUtils.showShort("这是友情链接");
                break;
            case R.id.menu_item_setting:
                ToastUtils.showShort("这是设置");
                break;
            case R.id.menu_item_about:
                ToastUtils.showShort("这是关于");
                break;
            default:
                break;
        }
        return false;
    }


}
