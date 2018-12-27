package example.com.playandroid.util;

import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * @author Richard_Y_Wang
 * @des 2018/11/11 15:24
 */
public class AnimUtil {
    /**
     *  这种写法是旋转到某个角度 比如写的是rotation 90 是旋转到90度
     *  如果使用rotationBy 比如写的是rotationBy 90 是顺时针旋转90度
     *  使view 旋转某个角度 中心旋转
     * @param view  被旋转的控件
     * @param time  动画时间
     * @param angle 旋转角度
     */
    public static void rotate(View view,long time,float angle,AnimatorListenerAdapter listener) {
        view.animate()
                .setDuration(time)   //设置动画时间
                .rotation(angle)      //设置旋转角度 是旋转到某个角度
                //.rotationBy(angle)  设置旋转角度 是旋转过这么多角度
                .setListener(listener)
                .start();           //开启动画、
    }

    /**
     *
     * @param view 被旋转的控件
     * @param isXAxis 是不是关于X轴 旋转
     * @param time 动画时间
     * @param angle 角度
     * @param listener  监听器回调
     */
    public static void rotateByAxis(View view,boolean isXAxis,long time,float angle,AnimatorListenerAdapter listener) {
        ViewPropertyAnimator v = view.animate().setDuration(time);
        if (isXAxis) v.rotationX(angle);
        else v.rotationY(angle);
        v.setListener(listener).start();
    }

    public static void moveAnd(View view,long time,float alpha,int y) {
        view.animate().setDuration(time).y(y).start();
    }

}
