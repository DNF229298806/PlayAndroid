package example.com.playandroid.content.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.databinding.ActivityCollectionBinding;
@Route(path = Constant.ActivityPath.CollectionActivity)
public class CollectionActivity extends BaseActivity<CollectionModel, ActivityCollectionBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModel(new CollectionModel(this));
        setSupportActionBar(getBinding().toolbar);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_collection;
    }
}
