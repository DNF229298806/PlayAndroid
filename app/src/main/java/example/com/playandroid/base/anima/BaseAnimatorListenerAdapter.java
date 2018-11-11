package example.com.playandroid.base.anima;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/**
 * @author Richard_Y_Wang
 * @des 2018/11/11 16:32
 */
public class BaseAnimatorListenerAdapter extends AnimatorListenerAdapter {
    public View view;

    public BaseAnimatorListenerAdapter(View view) {
        this.view = view;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        super.onAnimationStart(animation);
        view.setClickable(false);
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        view.setClickable(true);
    }
}
