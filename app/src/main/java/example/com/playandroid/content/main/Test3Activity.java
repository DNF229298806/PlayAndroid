package example.com.playandroid.content.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.R;
import example.com.playandroid.constant.Constant;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/12 15:03
 */
@Route(path = Constant.ActivityPath.TestActivity3)
public class Test3Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        RecyclerView rv = findViewById(R.id.test_recyclerview);
        List<TestItem> list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        String name = "Tom";
        int age = 0;
        for (int i = 0; i < 30; i++) {
            TestItem testItem = new TestItem(name + i, age + i, "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1014522747,1091495275&fm=15&gp=0.jpg");
            list.add(testItem);
        }
        MyTestAdapter adapter = new MyTestAdapter(R.layout.item_test, list);
        adapter.setOnItemClickListener((adapter1, view, position) -> ToastUtils.showLong("我是第"+position+"个"));
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            switch (view.getId()) {
                    case R.id.item_bt:
                        ToastUtils.showLong("我是第"+position+"按钮");
                        break;
                    case R.id.item_iv:
                        ToastUtils.showLong("我是"+position+"图片");
                        break;
                    case R.id.item_tv:
                        ToastUtils.showLong("我是"+position+"文本");
                        break;
                    default:
                        break;
            }
        });
        rv.setAdapter(adapter);
    }
}
