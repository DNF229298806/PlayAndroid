package example.com.playandroid.content.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseFragment;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentHomeBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/27 22:08
 */
public class HomeFragment extends BaseFragment<MainActivity,HomeModel,FragmentHomeBinding> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //初始化model 并与fragment进行绑定
        setModel(new HomeModel((MainActivity) getActivity(),this));
        return getBinding().getRoot();
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

}
