package example.com.playandroid;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.Stack;

import example.com.playandroid.network.Api;
import example.com.playandroid.network.ApiUtils;
import example.com.playandroid.util.TimberUtil;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/19
 */
public class App extends Application implements Application.ActivityLifecycleCallbacks{
    public static Api api;
    /**
     * 标识是否为调试
     */
    private boolean isDebug = true;

    /**
     * 设置是否为调试阶段
     *
     * @param debug
     */
    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    private Stack<Activity> stack = new Stack<>();
    private static final App app = new App();

    //static 代码段可以防止内存泄露 这边的是SmartRefreshLayout
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 初始化Application
         */
        if (isDebug) {
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);

        //设置log自动在apk为debug版本时打开，在release版本时关闭
        TimberUtil.setLogAuto();
        //也可以设置log一直开
        //TimberUtil.setLogDebug();

        //打印tag为类名
        Timber.v("---onCreate---");

        api = ApiUtils.INSTANCE.getApi(this);
    }

    public Stack<Activity> getStack() {
        return app.stack;
    }


    public static Activity getCurrentActivity() {
        return app.stack.lastElement();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        app.stack.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        app.stack.remove(activity);
    }

    public static String getResString(@StringRes int resId) {
        return App.getCurrentActivity().getString(resId);
    }


    public static Drawable getResDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(getCurrentActivity(), id);
    }

    @ColorInt
    public static int getResColor(@ColorRes int id) {
        return ContextCompat.getColor(getCurrentActivity(), id);
    }
}
