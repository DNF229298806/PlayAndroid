package example.com.playandroid.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 21:40
 */
public abstract class BaseActivity<T extends BaseModel, V extends ViewDataBinding> extends AppCompatActivity {
    private T mModel;
    private V mBinding;
    private SME sme;
    public ListCompositeDisposable list = new ListCompositeDisposable();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, setLayout());
        /*Timber.i("getLayout="+getLayout());
        mResources = getResources();*/
        sme.setModelEntity();
    }

    public interface SME{
        void setModelEntity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.dispose();
    }

    public void addDisposable(Disposable disposable) {
        list.add(disposable);
    }

    public void setSme(SME sme) {
        this.sme = sme;
    }

    public T getModel() {
        return mModel;
    }

    public void setModel(T model) {
        mModel = model;
    }

    public V getBinding() {
        return mBinding;
    }

    public void setBinding(V binding) {
        mBinding = binding;
    }


    public abstract int setLayout();

    public SME getSme() {
        return sme;
    }
}
