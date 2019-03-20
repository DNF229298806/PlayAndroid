package example.com.playandroid.content.project.tabpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.playandroid.R;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/20 10:25
 */
public class ProjectTabPageFragment extends Fragment {
    private int cid;

    public static ProjectTabPageFragment newInstance(int cid) {
        Bundle args = new Bundle();
        args.putInt("CID", cid);
        ProjectTabPageFragment fragment = new ProjectTabPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_tab_page, container, false);
        return view;
    }

}
