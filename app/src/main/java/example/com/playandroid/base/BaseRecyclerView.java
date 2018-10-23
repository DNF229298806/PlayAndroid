package example.com.playandroid.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author admin
 * @des 2018/10/23
 */
public class BaseRecyclerView<V extends BaseBindingAdapter> extends RecyclerView {
    private V adapter;

    public BaseRecyclerView(@NonNull Context context) {
        super(context);
    }

    public BaseRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Nullable
    @Override
    public V getAdapter() {
        return adapter;
    }

    public void setAdapter(V adapter) {
        this.adapter = adapter;
    }
}
