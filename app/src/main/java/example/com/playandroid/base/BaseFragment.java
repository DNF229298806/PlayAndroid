package example.com.playandroid.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.playandroid.BR;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/14 13:01
 */
public abstract class BaseFragment<K extends BaseActivity, T extends BaseFragmentModel, V extends ViewDataBinding> extends Fragment{
    private K mActivity;
    private T mModel;
    private V mBinding;
    private BaseEntity mEntity;

    @Nullable
    @Override
    @CallSuper
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, setLayout(), container, false);
        return mBinding.getRoot();
    }

    public void setModel(T model) {
        mModel = model;
        mBinding.setVariable(BR.vm, mModel);
    }

    public void setEntity(BaseEntity entity) {
        mEntity = entity;
        mBinding.setVariable(BR.entity, mEntity);
    }

    public T getModel() {
        return mModel;
    }

    public V getBinding() {
        return mBinding;
    }

    public void setBinding(V binding) {
        mBinding = binding;
    }

    public BaseEntity getEntity() {
        return mEntity;
    }

    public void setActivity(K activity) {
        mActivity = activity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public abstract @LayoutRes
    int setLayout();

}
