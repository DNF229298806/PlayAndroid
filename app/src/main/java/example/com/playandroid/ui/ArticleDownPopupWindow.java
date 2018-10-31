package example.com.playandroid.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.base.test.BindingAdapter;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.PopArticleDownBinding;
import razerdp.basepopup.BasePopupWindow;

/**
 * @author admin
 * @des 2018/10/31
 */
public class ArticleDownPopupWindow extends BasePopupWindow {
    private ArticleEntity entity;
    public ArticleDownPopupWindow(Context context) {
       super(context);
    }

    public ArticleDownPopupWindow(Context context, int w, int h) {
        super(context, w, h);
    }

    protected ArticleDownPopupWindow(Context context, int w, int h, boolean initImmediately) {
        super(context, w, h, initImmediately);
    }

    @Override
    public View onCreateContentView() {
        View view = createPopupById(R.layout.pop_article_down);
        PopArticleDownBinding bind = DataBindingUtil.bind(view);
        bind.setVm(this);
        return view;
    }

    @Override
    protected Animation onCreateShowAnimation() {
        /*AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);*/
        Animation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return null;
    }

    public void onUninterestedClick(View view) {
        ToastUtils.showShort("我真的不感兴趣啦");
        ((BindingAdapter)((MainActivity)App.getCurrentActivity()).getHomeFragment().getBinding().recyclerView.getAdapter()).removeToAdapter(entity);
        dismiss();
    }

    public void setEntity(ArticleEntity entity) {
        this.entity = entity;
    }
}
