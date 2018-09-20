package example.com.playandroid;

import android.content.res.Resources;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/19
 */
public class RegisterModel {
    private RegisterActivity mActivity;
    private Resources mResources;
    private RegisterEntity mEntity;

    public RegisterModel(RegisterActivity activity, Resources resources) {
        mActivity = activity;
        mResources = resources;
    }

    public RegisterModel(RegisterActivity activity, Resources resources, RegisterEntity entity) {
        this(activity, resources);
        mEntity = entity;
    }

    public void onRegisterClick(View view) {
        System.out.println("啊实打实大师大师大神阿萨德阿萨阿萨德");
        Timber.i("mEntity"+mEntity.toString());
        ToastUtils.showLong(mEntity.toString());
    }
}
