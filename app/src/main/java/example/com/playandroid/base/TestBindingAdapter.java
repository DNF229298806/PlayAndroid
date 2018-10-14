package example.com.playandroid.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import example.com.playandroid.R;
import example.com.playandroid.databinding.TestItemBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/14 20:43
 */
public class TestBindingAdapter extends RecyclerView.Adapter<BindingViewHolder<TestItemBinding>> {

    private List<TestEntity> data;

    private Context context;

    public TestBindingAdapter(List<TestEntity> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public BindingViewHolder<TestItemBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TestItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.test_item, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<TestItemBinding> holder, int position) {
        holder.getBinding().setVm(data.get(position));
        // 立刻刷新界面
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
