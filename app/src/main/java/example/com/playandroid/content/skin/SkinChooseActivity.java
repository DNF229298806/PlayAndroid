package example.com.playandroid.content.skin;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.databinding.ActivityChooseSkinBinding;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/15 16:51
 */
@Route(path = Constant.ActivityPath.SkinChooseActivity)
public class SkinChooseActivity extends BaseActivity<SkinChooseModel, ActivityChooseSkinBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModel(new SkinChooseModel(this));
    }

    @Override
    public int setLayout() {
        return R.layout.activity_choose_skin;
    }
}
