package example.com.playandroid.content.navigation;

import android.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author admin
 * @des 2018/12/12
 */
public class TestViewHolder<T extends ViewDataBinding> extends BaseViewHolder {
    protected final T mBinding;

    public TestViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
