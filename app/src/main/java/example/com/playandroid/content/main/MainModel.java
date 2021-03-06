package example.com.playandroid.content.main;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;

import de.hdodenhof.circleimageview.CircleImageView;
import example.com.playandroid.R;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.content.home.HomeFragment;
import example.com.playandroid.content.navigation.NavigationFragment;
import example.com.playandroid.content.project.ProjectFragment;
import example.com.playandroid.content.system.SystemFragment;
import example.com.playandroid.databinding.ActivityMainBinding;
import example.com.playandroid.util.ArouterUtil;
import example.com.playandroid.util.DogUtil;

import static example.com.playandroid.constant.Constant.FragmentType.HOME;
import static example.com.playandroid.constant.Constant.FragmentType.NAVIGATION;
import static example.com.playandroid.constant.Constant.FragmentType.PROJECT;
import static example.com.playandroid.constant.Constant.FragmentType.SYSTEM;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/27 21:49
 */
public class MainModel extends BaseModel<MainActivity, ActivityMainBinding> {
    private CircleImageView iv_Head;

    public MainModel(MainActivity activity) {
        super(activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //一进来就是MainActivity 如果SP中没有东西 那么跳转到LoginActivity
        SPUtils sp = SPUtils.getInstance(Constant.user_entity);
        if (TextUtils.isEmpty(sp.getString("username")) || TextUtils.isEmpty(sp.getString("password"))) {
            ArouterUtil.navigation(Constant.ActivityPath.LoginActivity);
            getActivity().finish();
        }
        View headerView = getBinding().navigationView.getHeaderView(0);
        iv_Head = headerView.findViewById(R.id.iv_head);
        iv_Head.setOnClickListener(v -> DogUtil.choosePicture(getActivity(), true));
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
                //ToastUtils.showLong("DataBindingAdapter home");
                return true;
            case R.id.it_project:
                activity.initFragment(projectFragment, PROJECT);
                //ToastUtils.showLong("DataBindingAdapter it_project");
                return true;
            case R.id.it_system:
                activity.initFragment(systemFragment, SYSTEM);
                //ToastUtils.showLong("DataBindingAdapter it_system");
                return true;
            case R.id.it_nav:
                activity.initFragment(navigationFragment, NAVIGATION);
                //ToastUtils.showLong("DataBindingAdapter it_nav");
                return true;
            default:
                break;
        }
        return false;
    }

    public void navigationClick(View view) {
        //ToastUtils.showShort("出现菜单栏");
        getBinding().drawableLayout.openDrawer(getBinding().navigationView);
    }

    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_collection:
                // subscribeOn 用于指定上游线程，observeOn 用于指定下游线程，
                // 多次用 subscribeOn 指定上游线程只有第一次有效，多次用 observeOn 指定下次线程，每次都有效；
                //  just 和 create操作符的区别
                // 如果Observable.just(Jsoup.connect(HOST + OPEN_API).get()) 这种写法
                // 他是先网络请求再把返回的结果放进去的 所以不行 会报networkOnMainThread的错误
                // 所以这里的线程切换实际上是没有用的 相当于1+1 在RxJava外面执行的 然后把结果2通过just发射出去
                // 如果Observable.create的话 是通过接口然后走的回调接口 所以这里的线程切换是有用的
                // 这样解析之后可以拿到头部的数据 之后再拿到里面的li标签的数据就可以了
                /*Observable.<Document>create(e -> e.onNext(Jsoup.connect(HOST + OPEN_API).get()))
                        .subscribeOn(Schedulers.newThread()).subscribe(
                        doc -> {
                            doc.select("div.area_r").select("h3").get(0).childNode(0).toString();
                        }, Throwable::printStackTrace
                );
                App.api.getCollectList(0)
                        .compose(new RestfulTransformer<>())
                        .subscribe(th -> ToastUtils.showLong("请求成功"), Throwable::printStackTrace);*/
                //ArouterUtil.navigation(Constant.ActivityPath.TestActivity);
                ArouterUtil.navigation(Constant.ActivityPath.CollectionActivity);
                //ToastUtils.showShort("这是收藏");
                break;
            case R.id.menu_item_navigation:
                getBinding().bnv.setSelectedItemId(R.id.it_nav);
                getBinding().drawableLayout.closeDrawers();
                //ToastUtils.showShort("这是导航");
                break;
            case R.id.menu_item_open_apis:
                ArouterUtil.navigation(Constant.ActivityPath.OpenApiActivity);
                //ToastUtils.showShort("这是开源Api");
                break;
            case R.id.menu_item_friend_link:
                //ToastUtils.showShort("这是友情链接");
                ArouterUtil.navigation(Constant.ActivityPath.SkinChooseActivity);
                break;
            case R.id.menu_item_setting:
                //ToastUtils.showShort("这是设置");
                ArouterUtil.navigation(Constant.ActivityPath.SettingActivity);
                break;
            case R.id.menu_item_about:
                //ToastUtils.showShort("这是关于");
                //gotoAbout();
                break;
            default:
                break;
        }
        return false;
    }

   /* private void gotoAbout() {
        LibsBuilder lb = new LibsBuilder();
        lb.aboutAppName = "玩Android";
        lb.activityTitle = "感谢";
        lb.withAboutIconShown(true)
                .withVersionShown(true)
                .withAboutVersionShown(true)
                .withSortEnabled(true)
                .withAboutDescription("玩Android是由王一波独立开发的毕业设计作品，同时也是一款用于获取，阅读Android开发技术与资讯的App。本App的接口来自hongyang的个人网站,并在开发中使用了以下第三方库，特此感谢！")
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .start(getActivity());
    }*/
}
