package example.com.playandroid.content.register;

import android.os.Bundle;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.databinding.ActivityRegisterBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/19
 */
public class RegisterActivity extends BaseActivity<RegisterModel,ActivityRegisterBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*想办法简化这边的代码 主要是因为model和Entity没有办法在create的时候一步到位 想办法吧这个model和entity穿进去4
        * 然后最好是把set保存的这段代码卸载base里面
        * */
        super.onCreate(savedInstanceState);
        setModel(new RegisterModel(this));
        setEntity(new RegisterEntity());
        setSupportActionBar(getBinding().toolbar);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
