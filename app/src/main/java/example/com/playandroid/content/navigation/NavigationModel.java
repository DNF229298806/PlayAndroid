package example.com.playandroid.content.navigation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentNavigationBinding;
import timber.log.Timber;

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
       /* List list = new ArrayList<TestEntity>();
        for (int i = 0; i < 20; i++) {
            TestEntity test = new TestEntity();
            test.setContent("I asasd "+i);
            list.add(test);
        }*/
        List<MultiItemEntity> list = generateData();
        ExpandableItemAdapter adapter = new ExpandableItemAdapter(list);
        RecyclerView recyclerView = getBinding().recyclerView;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        //adapter.expandAll();
        /*     recyclerView.setAdapter(new TestBindingAdapter(list, recyclerView.getContext()));*/
    }

    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count =10;
        int personCount = 9;
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        String[] niubiList = {"Android", "IOS","Android", "IOS","Android", "IOS","Android", "IOS","Android", "IOS"};
        String[] nameList = {"Java", "Kotlin", "Dart", "Flutter","Py","PHP","C","C++","HTML"};
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item(niubiList[i], niubiList[i]);
            for (int j = 0; j < personCount; j++) {
                Person person = new Person(nameList[j], j);
                lv0.addSubItem(person);
            }
            res.add(lv0);
            Timber.i("走了一次！！！！！");
        }
        return res;
    }
}
