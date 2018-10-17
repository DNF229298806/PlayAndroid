package example.com.playandroid.content.home;

import android.content.Context;

import java.util.List;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseBindingAdapter;
import example.com.playandroid.base.TestEntity;
import example.com.playandroid.databinding.TestItemBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/17 21:00
 */
public class TestBindingAdapter extends BaseBindingAdapter<TestEntity,TestItemBinding> {


    public TestBindingAdapter(List<TestEntity> data, Context context) {
        super(data, context);
    }

    @Override
    public int setLayout() {
        return R.layout.test_item;
    }
}
