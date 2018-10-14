package example.com.playandroid.content.system;

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
import example.com.playandroid.databinding.FragmentSystemBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:00
 */
public class SystemFragment extends BaseFragment<MainActivity,SystemModel,FragmentSystemBinding> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setModel(new SystemModel((MainActivity) getActivity(), this));
        return getBinding().getRoot();
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_system;
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
