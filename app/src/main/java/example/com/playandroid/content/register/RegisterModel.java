package example.com.playandroid.content.register;

import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.databinding.ActivityRegisterBinding;
import example.com.playandroid.network.transform.ErrorTransform;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/19
 */
public class RegisterModel extends BaseModel<RegisterActivity,ActivityRegisterBinding> {

    public RegisterModel(RegisterActivity activity) {
        super(activity);
    }

    public void onRegisterClick(View view) {
        RegisterEntity regEntity = getBinding().getEntity();
        System.out.println("啊实打实大师大师大神阿萨德阿萨阿萨德");
        Timber.e("mEntity=%1s",regEntity.toString());
        ToastUtils.showLong(regEntity.toString());
        addDisposable(App.api.register(regEntity.getUsername(), regEntity.getPassword(), regEntity.getRepassword())
                .compose(new ErrorTransform<>())
                .subscribe(
                        entity -> ToastUtils.showLong("注册成功"),
                        a -> ToastUtils.showLong(a.getMessage())
                ));
    }
}
