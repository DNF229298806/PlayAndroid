package example.com.playandroid.content.system;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.adapter.SystemBindingAdapter;
import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentSystemBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:08
 */
public class SystemModel extends BaseFragmentModel<MainActivity, SystemFragment, FragmentSystemBinding> {

    public SystemModel(MainActivity activity, SystemFragment fragment) {
        super(activity, fragment);
    }

    private void accept(List<SystemEntity> list) {
        RecyclerView recyclerView = getBinding().recyclerView;
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal
        layoutManager.setFlexDirection(FlexDirection.ROW);
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        //layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SystemBindingAdapter(list, recyclerView.getContext()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.list_divider);
        itemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        addDisposable(App.api.getSystemList()
                .compose(new RestfulTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::accept, Throwable::printStackTrace));
        /*List<TestEntity> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            TestEntity test = new TestEntity();
            test.setContent("I asasd "+i);
            list.add(test);
        }*/
      /*  RecyclerView recyclerView = getBinding().recyclerView;
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal
        layoutManager.setFlexDirection(FlexDirection.ROW);
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SystemBindingAdapter(list, recyclerView.getContext()));
        //recyclerView.setAdapter(new TestBindingAdapter(list, recyclerView.getContext()));*/

    }
}
