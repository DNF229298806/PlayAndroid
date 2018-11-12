package example.com.playandroid.content.register;

import android.os.Bundle;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<LoginModel,ActivityLoginBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModel(new LoginModel(this));
        //2018年11月12日22:18:22
        setEntity(new UserEntity());
    }

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }
}
