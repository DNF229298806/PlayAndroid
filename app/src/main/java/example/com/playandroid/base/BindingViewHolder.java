package example.com.playandroid.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/14 19:56
 */
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    protected final T mBinding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
