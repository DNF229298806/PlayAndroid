package example.com.playandroid.content.setting;

import android.app.AlertDialog;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import example.com.playandroid.base.BaseModel;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.databinding.ActivitySettingBinding;
import example.com.playandroid.util.ArouterUtil;
import example.com.playandroid.util.DataCleanManagerUtil;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/13 14:10
 */
public class SettingModel extends BaseModel<SettingActivity, ActivitySettingBinding> {

    public SettingModel(SettingActivity activity) {
        super(activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            getBinding().tvCache.setText(DataCleanManagerUtil.getTotalCacheSize(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkUpdate(View view) {
       //Observable.just(1).delay(2, TimeUnit.SECONDS)

    }

    public void onClearCacheClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("清除缓存");
        builder.setMessage("清除缓存会导致下载的内容删除，是否确定？");
        builder.setPositiveButton("删除", (dialog, which) -> {
            try {
                DataCleanManagerUtil.clearAllCache(getActivity());
                getBinding().tvCache.setText(DataCleanManagerUtil.getTotalCacheSize(getActivity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        builder.setNegativeButton("取消", (dialog, which) -> {
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onFeedBackClick(View view) {

    }

    public void onExitClick(View view) {
        SPUtils sp = SPUtils.getInstance(Constant.user_entity);
        sp.remove("username");
        sp.remove("password");
        //界面跳转
        ArouterUtil.navigation(Constant.ActivityPath.LoginActivity);
    }

    public void onAboutClick(View view) {
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
    }
}
