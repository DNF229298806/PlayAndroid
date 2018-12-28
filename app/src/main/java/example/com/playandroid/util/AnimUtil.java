package example.com.playandroid.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewPropertyAnimator;

import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/11/11 15:24
 */
public class AnimUtil {
    private static boolean rotate = false;

    /**
     * 这种写法是旋转到某个角度 比如写的是rotation 90 是旋转到90度
     * 如果使用rotationBy 比如写的是rotationBy 90 是顺时针旋转90度
     * 使view 旋转某个角度 中心旋转
     *
     * @param view  被旋转的控件
     * @param time  动画时间
     * @param angle 旋转角度
     */
    public static void rotate(View view, long time, float angle, AnimatorListenerAdapter listener) {
        view.animate()
                .setDuration(time)   //设置动画时间
                .rotation(angle)      //设置旋转角度 是旋转到某个角度
                //.rotationBy(angle)  设置旋转角度 是旋转过这么多角度
                .setListener(listener)
                .start();           //开启动画、
    }

    /**
     * @param view     被旋转的控件
     * @param isXAxis  是不是关于X轴 旋转
     * @param time     动画时间
     * @param angle    角度
     * @param listener 监听器回调
     */
    public static void rotateByAxis(View view, boolean isXAxis, long time, float angle, AnimatorListenerAdapter listener) {
        ViewPropertyAnimator v = view.animate().setDuration(time);
        if (isXAxis)
            v.rotationX(angle);
        else
            v.rotationY(angle);
        v.setListener(listener).start();
    }

    public static void moveAnd(View view, long time, float alpha, int y) {
        view.animate().setDuration(time).y(y).start();
    }


    public static void initShowOut(final View v) {
        v.setVisibility(View.GONE);
        v.setTranslationY(v.getHeight());
        v.setAlpha(0f);
    }

    public static void initPopLayout(FloatingActionButton fab, View backDrop, View... popLayouts) {
        rotate = false;
        if (popLayouts == null || popLayouts.length == 0 || backDrop == null)
            return;

        for (View ll : popLayouts) {
            initShowOut(ll);
        }
        fab.setOnClickListener(v -> toggleFabMode(fab, popLayouts, backDrop));
        backDrop.setOnClickListener(v -> toggleFabMode(fab, popLayouts, backDrop));

    }

    private static void toggleFabMode(View v, View[] popLayouts, View backDrop) {

        rotate = rotateFab(v, !rotate);
        v.setTag(rotate);
        if (rotate) {
            for (View ll : popLayouts) {
                showIn(ll);
            }
            backDrop.setVisibility(View.VISIBLE);
        } else {
            for (View ll : popLayouts) {
                showOut(ll);
            }
            backDrop.setVisibility(View.GONE);
        }
    }

    public static boolean rotateFab(final View v, boolean rotate) {
        v.animate().setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                })
                .rotation(rotate ? 135f : 0f);
        return rotate;
    }

    public static void showIn(final View v) {
        Timber.i("showIn start!!!!!top:%1s,right:%2s,bottom:%3s,left:%4s", v.getTop(), v.getRight(), v.getBottom(), v.getLeft());
        Timber.i("showIn start!!!!!height:%1s,width:%2s", v.getHeight(), v.getWidth());
        Timber.i("showIn start!!!!!TranslationY:%1s",v.getTranslationY());
        v.setVisibility(View.VISIBLE);
        v.setAlpha(0f);
        v.setTranslationY(v.getHeight());
        v.animate()
                .setDuration(200)
                .translationY(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Timber.i("showIn end!!!!!top:%1s,right:%2s,bottom:%3s,left:%4s", v.getTop(), v.getRight(), v.getBottom(), v.getLeft());
                        Timber.i("showIn end!!!!!height:%1s,width:%2s", v.getHeight(), v.getWidth());
                        Timber.i("showIn end!!!!!TranslationY:%1s",v.getTranslationY());
                    }
                })
                /*.alpha(1f)*/
                .start();
    }

    public static void showOut(final View v) {
        Timber.i("showOut start!!!!!top:%1s,right:%2s,bottom:%3s,left:%4s", v.getTop(), v.getRight(), v.getBottom(), v.getLeft());
        Timber.i("showOut start!!!!!height:%1s,width:%2s", v.getHeight(), v.getWidth());
        Timber.i("showOut start!!!!!TranslationY:%1s",v.getTranslationY());
        v.setVisibility(View.VISIBLE);
        v.setAlpha(1f);
        v.setTranslationY(0);
        v.animate()
                .setDuration(200)
                .translationY(v.getHeight())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Timber.i("showOut end!!!!!top:%1s,right:%2s,bottom:%3s,left:%4s", v.getTop(), v.getRight(), v.getBottom(), v.getLeft());
                        Timber.i("showOut end!!!!!height:%1s,width:%2s", v.getHeight(), v.getWidth());
                        Timber.i("showOut end!!!!!TranslationY:%1s",v.getTranslationY());
                        v.setVisibility(View.GONE);
                        super.onAnimationEnd(animation);
                    }
                }).alpha(0f)
                .start();
    }
}
