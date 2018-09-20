package example.com.playandroid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.com.playandroid.databinding.ActivityRegisterBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/19
 */
public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding mBinding;
    private RegisterModel mModel;
    private RegisterEntity mEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mEntity = new RegisterEntity();
        mBinding.setEntity(mEntity);
        mModel = new RegisterModel(this, getResources(), mEntity);
        mBinding.setVm(mModel);
    }
}
