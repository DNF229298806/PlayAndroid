package example.com.playandroid.content.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.ObservableBoolean;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.base.anima.BaseAnimatorListenerAdapter;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.databinding.ActivityLoginBinding;
import example.com.playandroid.network.transform.ErrorTransform;
import example.com.playandroid.network.transform.RestfulTransformer;
import example.com.playandroid.util.AnimUtil;
import example.com.playandroid.util.DogUtil;
import io.reactivex.disposables.Disposable;

/**
 * @author Richard_Y_Wang
 * @des 2018/11/6 18:53
 * 明天回来对接接口 登录注册接口 注意要在一个按钮的点击事件里面分别处理登录和注册
 */
public class LoginModel extends BaseModel<LoginActivity, ActivityLoginBinding> {
    //拿来判断是想要进行登录还是注册
    private boolean isLogin = true;
    //拿来判断是不是对登录/注册的按钮进行了点击或者转化
    public ObservableBoolean isChange = new ObservableBoolean(true);
    public ObservableBoolean isProgressBarShow = new ObservableBoolean(false);
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
        //开始网络请求的同时开始显示ProgressBar
        isProgressBarShow.set(true);
        UserEntity user = getBinding().getEntity();
        Disposable disposable;
        if (isLogin) {
            disposable = App.api.login(user.getUsername(), user.getPassword())
                    .compose(new ErrorTransform<>())
                    .subscribe(info -> {
                        if (info.getErrorCode() == 0) {
                            ToastUtils.showLong("登录成功");
                            UserEntity returnUser = info.getData();
                            initUser(user, returnUser);
                            //界面跳转 以及把返回的User使用SP进行保存
                            DogUtil.saveToSpByReflect(user, SPUtils.getInstance(Constant.user_entity));
                        }
                        //网络请求结束 ProgressBar进行隐藏
                        isProgressBarShow.set(false);
                    }, Throwable::printStackTrace);
        } else {
            disposable = App.api.register(user.getUsername(), user.getPassword(), user.getRepassword())
                    .compose(new RestfulTransformer<>())
                    .subscribe(entity -> ToastUtils.showLong("注册成功"),
                            a -> ToastUtils.showLong(a.getMessage()));
        }
        addDisposable(disposable);
    }

    private void initUser(UserEntity user, UserEntity returnUser) {
        user.setCollectIds(returnUser.getCollectIds());
        user.setEmail(returnUser.getEmail());
        user.setIcon(returnUser.getIcon());
        user.setId(returnUser.getId());
        user.setType(returnUser.getType());
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
