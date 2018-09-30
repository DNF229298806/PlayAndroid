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
    private ActivityRegisterBinding mBinding;
    private RegisterModel mModel;
    private RegisterEntity mEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*想办法简化这边的代码 主要是因为model和Entity没有办法在create的时候一步到位 想办法吧这个model和entity穿进去4
        * 然后最好是把set保存的这段代码卸载base里面
        * */
        setSme(() -> {
            getBinding().setEntity(mEntity = new RegisterEntity());
            RegisterModel model = new RegisterModel(this);
            setModel(model);
            getBinding().setVm(getModel());
            getModel().setEntity(mEntity);
        });
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().toolbar);
        /*mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mEntity = new RegisterEntity();
        mBinding.setEntity(mEntity);
        mModel = new RegisterModel(this, getResources(), mEntity);
        mBinding.setVm(mModel);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int setLayout() {
        return R.layout.activity_register;
    }

    public RegisterEntity getEntity() {
        return mEntity;
    }

    public void setEntity(RegisterEntity entity) {
        mEntity = entity;
    }
}
