package example.com.playandroid.base;

import android.databinding.ViewDataBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/2 14:42
 */
public abstract class BaseEntity<T extends ViewDataBinding> implements Mult<T> {
    protected T binding;
}
