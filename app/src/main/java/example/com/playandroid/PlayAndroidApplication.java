package example.com.playandroid;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import example.com.playandroid.util.TimberUtil;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/19
 */
public class PlayAndroidApplication extends Application {
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

    }

}
