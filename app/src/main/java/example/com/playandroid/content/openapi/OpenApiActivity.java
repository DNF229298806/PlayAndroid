package example.com.playandroid.content.openapi;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.databinding.ActivityOpenApiBinding;
@Route(path = Constant.ActivityPath.OpenApiActivity)
public class OpenApiActivity extends BaseActivity<OpenApiModel,ActivityOpenApiBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModel(new OpenApiModel(this));
        setSupportActionBar(getBinding().toolbar);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_open_api;
    }
}
