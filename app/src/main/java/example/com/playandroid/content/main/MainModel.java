package example.com.playandroid.content.main;

import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import org.jsoup.Jsoup;

import java.io.IOException;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.content.home.HomeFragment;
import example.com.playandroid.content.navigation.NavigationFragment;
import example.com.playandroid.content.project.ProjectFragment;
import example.com.playandroid.content.system.SystemFragment;
import example.com.playandroid.databinding.ActivityMainBinding;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static example.com.playandroid.constant.Constant.FragmentType.HOME;
import static example.com.playandroid.constant.Constant.FragmentType.NAVIGATION;
import static example.com.playandroid.constant.Constant.FragmentType.PROJECT;
import static example.com.playandroid.constant.Constant.FragmentType.SYSTEM;
import static example.com.playandroid.network.Api.HOST;
import static example.com.playandroid.network.Api.OPEN_API;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/27 21:49
 */
public class MainModel extends BaseModel<MainActivity, ActivityMainBinding> {

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
                activity.initFragment(homeFragment, HOME);
                ToastUtils.showLong("DataBindingAdapter home");
                return true;
            case R.id.it_project:
                activity.initFragment(projectFragment, PROJECT);
                ToastUtils.showLong("DataBindingAdapter it_project");
                return true;
            case R.id.it_system:
                activity.initFragment(systemFragment, SYSTEM);
                ToastUtils.showLong("DataBindingAdapter it_system");
                return true;
            case R.id.it_nav:
                activity.initFragment(navigationFragment, NAVIGATION);
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
                //因为api使用了GSON作为解析器 所以无法用来解析HTML
                //这里使用了RxJava直接进行请求
              /*  App.api.getOpenAPIS().subscribe(
                        html -> {
                            Document doc = Jsoup.parse(html);
                            Timber.i("成功了？：%s",
                                    doc.select("div.area_r").select("h3").get(0).childNode(0).toString());
                        },Throwable::printStackTrace
                );*/
              //这样解析之后可以拿到头部的数据 之后再拿到里面的li标签的数据就可以了
                try {
                    Observable.just(Jsoup.connect(HOST + OPEN_API).get()).subscribeOn(Schedulers.newThread()).subscribe();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Observable.just(1)
                        .observeOn(Schedulers.newThread())
                        .map(i -> Jsoup.connect(HOST+OPEN_API).get())
                        .subscribe(doc -> {
                            doc.select("div.area_r").select("h3").get(0).childNode(0).toString();
                        }, Throwable::printStackTrace);
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
