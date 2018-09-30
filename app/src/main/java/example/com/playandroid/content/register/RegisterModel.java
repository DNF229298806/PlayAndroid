package example.com.playandroid.content.register;

import android.content.res.Resources;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.network.transform.ErrorTransform;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/19
 */
public class RegisterModel extends BaseModel<RegisterActivity> {
    private RegisterActivity mActivity;
    private Resources mResources;
    private RegisterEntity mEntity;

    public RegisterModel(RegisterActivity activity) {
        super(activity);
    }

    public void onRegisterClick(View view) {
        System.out.println("啊实打实大师大师大神阿萨德阿萨阿萨德");
        Timber.e("mEntity=%1s",mEntity.toString());
        ToastUtils.showLong(mEntity.toString());
        mActivity.addDisposable(App.api.register(mEntity.getUsername(), mEntity.getPassword(), mEntity.getRepassword())
                .compose(new ErrorTransform<>())
                .subscribe(
                        entity -> ToastUtils.showLong("注册成功"),
                        a -> ToastUtils.showLong(a.getMessage())
                ));
    }

    public RegisterEntity getEntity() {
        return mEntity;
    }

    public void setEntity(RegisterEntity entity) {
        mEntity = entity;
    }
}
