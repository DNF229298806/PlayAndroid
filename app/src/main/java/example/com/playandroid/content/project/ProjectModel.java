package example.com.playandroid.content.project;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.App;
import example.com.playandroid.adapter.TabViewPagerAdapter;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.content.project.tabpage.TabViewPagerAdapterItem;
import example.com.playandroid.databinding.FragmentProjectBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:08
 */
public class ProjectModel extends BaseFragmentModel<MainActivity, ProjectFragment, FragmentProjectBinding> {

    public ProjectModel(MainActivity activity, ProjectFragment fragment) {
        super(activity, fragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.api.getProjectCategories()
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(systemEntities -> {
                    TabViewPagerAdapter adapter = new TabViewPagerAdapter(getFragment().getChildFragmentManager(),
                            TabViewPagerAdapterItem.createProjectTabFragments(systemEntities));
                    getBinding().viewPager.setAdapter(adapter);
                    getBinding().tabLayout.setupWithViewPager(getBinding().viewPager);
                }, throwable -> {
                    throwable.printStackTrace();
                    ToastUtils.showLong("数据获取失败");
                });

    }
}
