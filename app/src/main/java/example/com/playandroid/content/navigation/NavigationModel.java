package example.com.playandroid.content.navigation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

import example.com.playandroid.App;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentNavigationBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

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
        initData();
    }

    private void initData() {
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
                    adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                    adapter.bindToRecyclerView(recyclerView);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                }, Throwable::printStackTrace));
    }

}
