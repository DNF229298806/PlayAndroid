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
    }

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }
}
