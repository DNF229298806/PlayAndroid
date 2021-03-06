package example.com.playandroid.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import example.com.playandroid.BR;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 21:40
 */
public abstract class BaseActivity<T extends BaseModel, V extends ViewDataBinding> extends AppCompatActivity{
    private T mModel;
    private V mBinding;
    private BaseEntity mEntity;


    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, setLayout());
        //doOnCreate();
    }

    //protected abstract void doOnCreate();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public void setModel(T model) {
        mModel = model;
        mBinding.setVariable(BR.vm, mModel);
    }

    public T getModel() {
        return mModel;
    }

    public BaseEntity getEntity() {
        return mEntity;
    }

    public void setEntity(BaseEntity entity) {
        mEntity = entity;
        mBinding.setVariable(BR.entity, mEntity);
    }

    public V getBinding() {
        return mBinding;
    }

    public void setBinding(V binding) {
        mBinding = binding;
    }


    public abstract @LayoutRes
    int setLayout();


}
