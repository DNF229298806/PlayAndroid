package example.com.playandroid.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.playandroid.BR;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/14 13:01
 */
public abstract class BaseFragment<K extends BaseActivity, T extends BaseFragmentModel, V extends ViewDataBinding> extends Fragment{
    private K mActivity;
    private T mModel;
    private V mBinding;
    private BaseEntity mEntity;
    public ListCompositeDisposable list = new ListCompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, setLayout(), container, false);
        //doOnCreateView();
        return mBinding.getRoot();
    }

    //protected abstract void doOnCreateView();

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

    public K getMyActivity() {
        return mActivity;
    }

    public void setActivity(K activity) {
        mActivity = activity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        list.dispose();
    }

    public void addDisposable(Disposable disposable) {
        list.add(disposable);
    }

    public abstract @LayoutRes
    int setLayout();

}
