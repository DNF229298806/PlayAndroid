package example.com.playandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseBindingAdapter;
import example.com.playandroid.base.BaseBindingViewHolder;
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

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder<TestItemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ViewGroup.LayoutParams lp =  holder.getBinding().getRoot().getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams)  holder.getBinding().getRoot().getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }


    }

}
