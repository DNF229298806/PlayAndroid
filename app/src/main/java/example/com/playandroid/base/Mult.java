package example.com.playandroid.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

/**
 * @author admin
 * @des 2018/10/26
 */
public interface Mult<T extends ViewDataBinding> {
    @LayoutRes
    int getViewType();
    T getDataBinding();
    void setDataBinding(T t);
}

