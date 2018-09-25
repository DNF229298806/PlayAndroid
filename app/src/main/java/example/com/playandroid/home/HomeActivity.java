package example.com.playandroid.home;

import android.os.Bundle;

import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity {
    private ActivityHomeBinding mBinding;
    private HomeModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* mModel = new HomeModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mBinding.setVm(mModel);*/
    }
}
