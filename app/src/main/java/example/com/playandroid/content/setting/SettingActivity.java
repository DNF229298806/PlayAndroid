package example.com.playandroid.content.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.databinding.ActivityLoginBinding;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/13 14:10
 */
@Route(path = Constant.ActivityPath.SettingActivity)
public class SettingActivity extends BaseActivity<SettingModel, ActivityLoginBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModel(new SettingModel(this));
    }

    @Override
    public int setLayout() {
        return R.layout.activity_setting;
    }
}
