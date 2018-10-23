package example.com.playandroid.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/20 15:50
 */
public class SuperRecyclerView extends RecyclerView {
    private OnBottomCallback mOnBottomCallback;

    public interface OnBottomCallback {
        void onBottom();
    }

    public SuperRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public SuperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SuperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnBottomCallback(OnBottomCallback onBottomCallback) {
        this.mOnBottomCallback = onBottomCallback;
    }

    public boolean isSlideToBottom() {
        return computeVerticalScrollExtent() + computeVerticalScrollOffset() >= computeVerticalScrollRange();

    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (isSlideToBottom()) {
            mOnBottomCallback.onBottom();
        }
    }


   /* @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (isSlideToBottom()) {
            mOnBottomCallback.onBottom();
        }
    }*/
}
