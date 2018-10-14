package example.com.playandroid.content.project;

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
import example.com.playandroid.databinding.FragmentProjectBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:00
 */
public class ProjectFragment extends BaseFragment<MainActivity,ProjectModel,FragmentProjectBinding> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setModel(new ProjectModel((MainActivity) getActivity(), this));
        return getBinding().getRoot();
    }
    @Override
    public int setLayout() {
        return R.layout.fragment_project;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /* private FragmentProjectBinding mBinding;
    private ProjectModel mModel;*/

   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_project, container, false);
        mModel = new ProjectModel(this);
        mBinding.setVm(mModel);
        return mBinding.getRoot();
    }*/
}

