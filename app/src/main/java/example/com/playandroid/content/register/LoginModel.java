package example.com.playandroid.content.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.ObservableBoolean;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.base.anima.BaseAnimatorListenerAdapter;
import example.com.playandroid.databinding.ActivityLoginBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import example.com.playandroid.util.AnimUtil;

/**
 * @author Richard_Y_Wang
 * @des 2018/11/6 18:53
 * 明天回来对接接口 登录注册接口 注意要在一个按钮的点击事件里面分别处理登录和注册
 */
public class LoginModel extends BaseModel<LoginActivity, ActivityLoginBinding> {
    private boolean isLogin = true;
    public ObservableBoolean isChange = new ObservableBoolean(true);

    public LoginModel(LoginActivity activity) {
        super(activity);
    }

    public void onFABClick(View view) {
        isLogin = !isLogin;
        //如果是登录的话 顺时针旋转180 否则反向旋转
        //AnimUtil.rotate(view,300,isLogin.get()?180:-180);
        AnimUtil.rotate(view, 300, isLogin ? 180 : 0, new BaseAnimatorListenerAdapter(view));
        AnimUtil.rotateByAxis(getBinding().flTitle, true, 300, 90, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isChange.set(isLogin);
                AnimUtil.rotateByAxis(getBinding().flTitle, true, 300, 0, null);
                ToastUtils.showLong("翻转结束了");
            }
        });
    }

    public void onRegisterOrLoginClick(View view) {
        UserEntity user = getBinding().getEntity();
        if (isLogin) {
            ToastUtils.showLong("登录成功");
        } else {
            addDisposable(
                    App.api.register(user.getUsername(), user.getPassword(), user.getRepassword()).compose(new RestfulTransformer<>())
                            .subscribe(
                                    entity -> ToastUtils.showLong("注册成功"),
                                    a -> ToastUtils.showLong(a.getMessage())
                            ));
        }
    }

    public void userAfterTextChanged(Editable s) {
        String text = s.toString();
        if (TextUtils.isEmpty(text)) {
            getBinding().tieUsername.setError("账号不能为空！");
        }else{
            getBinding().tieUsername.setError(null);
        }
    }
    public void passwordAfterTextChanged(Editable s) {
        String text = s.toString();
        if (TextUtils.isEmpty(text)) {
            getBinding().tiePassword.setError("密码不能为空！");
        }else{
            getBinding().tiePassword.setError(null);
        }
    }
    public void repasswordAfterTextChanged(Editable s) {
        String text = s.toString();
        UserEntity entity = getBinding().getEntity();
        if (TextUtils.isEmpty(text)) {
            getBinding().tieConfirmPassword.setError("确认密码不能为空！");
        }else if (!entity.getPassword().equals(entity.getRepassword())) {
            getBinding().tieConfirmPassword.setError("两次输入的密码不一致！");
        }else{
            getBinding().tieConfirmPassword.setError(null);
        }
    }

    public void isTextNull() {

    }
}
