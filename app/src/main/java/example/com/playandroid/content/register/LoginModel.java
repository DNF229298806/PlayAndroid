package example.com.playandroid.content.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.ObservableBoolean;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.base.BaseModel;
import example.com.playandroid.base.anima.BaseAnimatorListenerAdapter;
import example.com.playandroid.databinding.ActivityLoginBinding;
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
}
