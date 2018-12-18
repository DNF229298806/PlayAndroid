package example.com.playandroid.content.navigation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentNavigationBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:08
 */
public class NavigationModel extends BaseFragmentModel<MainActivity, NavigationFragment, FragmentNavigationBinding> {
    private ExpandableItemAdapter adapter;

    public NavigationModel(MainActivity activity, NavigationFragment fragment) {
        super(activity, fragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RecyclerView recyclerView = getBinding().recyclerView;
        addDisposable(App.api.getNavigationList()
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(navList -> {
                    ArrayList<MultiItemEntity> res = new ArrayList<>();
                    for (NavigationTitleEntity titleEntity : navList) {
                        for (NavigationArticlesEntity articlesEntity : titleEntity.getArticles()) {
                            titleEntity.addSubItem(articlesEntity);
                        }
                        res.add(titleEntity);
                    }
                    adapter = new ExpandableItemAdapter(res);
                    adapter.bindToRecyclerView(recyclerView);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                }, Throwable::printStackTrace));
    }

    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count = 10;
        int personCount = 9;
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        String[] niubiList = {"Android", "IOS", "Android", "IOS", "Android", "IOS", "Android", "IOS", "Android", "IOS"};
        String[] nameList = {"Java", "Kotlin", "Dart", "Flutter", "Py", "PHP", "C", "C++", "HTML"};
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
