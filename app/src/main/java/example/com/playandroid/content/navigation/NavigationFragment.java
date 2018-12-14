package example.com.playandroid.content.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseFragment;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentNavigationBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:00
 */
public class NavigationFragment extends BaseFragment<MainActivity,NavigationModel,FragmentNavigationBinding> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setModel(new NavigationModel((MainActivity) getActivity(), this));
       /* RefreshLayout refreshLayout = getBinding().refreshLayout;
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000*//*,false*//*);//传入false表示刷新失败
                ToastUtils.showShort("上啦~~~~~~~~~~~~~");
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000*//*,false*//*);//传入false表示加载失败
                ToastUtils.showShort("下啦~~~~~~~~~~~~~");
            }
        });*/

        return getBinding().getRoot();
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
