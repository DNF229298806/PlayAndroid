package example.com.playandroid.content.main;

import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.constant.Constant;

@Route(path = Constant.ActivityPath.TestActivity)
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ImageView imageView = findViewById(R.id.test_im);
        imageView.setOnClickListener(v -> {
         /*   ActivityOptionsCompat optionsCompat =
                    ActivityOptionsCompat.makeScaleUpAnimation(imageView,
                            imageView.getWidth() / 2, imageView.getHeight() / 2, 0, 0);
            ARouter.getInstance().build(Constant.ActivityPath.TestActivity2).withOptionsCompat(optionsCompat).navigation();*/

            ViewCompat.setTransitionName(imageView,"im");
            ActivityOptionsCompat op = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "im");
            //注意这样写一定要在navigation中传入Context 参数 否则不会进行跳转
            ARouter.getInstance().build(Constant.ActivityPath.TestActivity2).withOptionsCompat(op).navigation(App.getCurrentActivity());
           /* ArouterUtil.navigation(Constant.ActivityPath.TestActivity2);*/
        });
    }
}
