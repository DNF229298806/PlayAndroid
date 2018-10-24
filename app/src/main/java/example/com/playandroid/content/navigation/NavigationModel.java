package example.com.playandroid.content.navigation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.adapter.TestBindingAdapter;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.base.TestEntity;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentNavigationBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:08
 */
public class NavigationModel extends BaseFragmentModel<MainActivity,NavigationFragment,FragmentNavigationBinding> {

    public NavigationModel(MainActivity activity, NavigationFragment fragment) {
        super(activity, fragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        List list = new ArrayList<TestEntity>();
        for (int i = 0; i < 20; i++) {
            TestEntity test = new TestEntity();
            test.setContent("I asasd "+i);
            list.add(test);
        }
        RecyclerView recyclerView = getBinding().recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new TestBindingAdapter(list, recyclerView.getContext()));
    }
}
