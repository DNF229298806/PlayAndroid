package example.com.playandroid.content.project;

import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.content.main.MainActivity;
import example.com.playandroid.databinding.FragmentProjectBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:08
 */
public class ProjectModel extends BaseFragmentModel<MainActivity,ProjectFragment,FragmentProjectBinding> {


    public ProjectModel(MainActivity activity, ProjectFragment fragment) {
        super(activity, fragment);
    }
}
