package example.com.playandroid.content.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import example.com.playandroid.R;
import example.com.playandroid.constant.Constant;

@Route(path = Constant.ActivityPath.TestActivity2)
public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        PhotoView pv = findViewById(R.id.pv);
        Glide.with(this).load(R.mipmap.timg).into(pv);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
