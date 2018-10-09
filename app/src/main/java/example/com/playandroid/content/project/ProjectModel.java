package example.com.playandroid.content.project;

import android.support.v4.app.Fragment;

import example.com.playandroid.base.BaseModel;
import example.com.playandroid.content.main.MainActivity;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:08
 */
public class ProjectModel extends BaseModel<MainActivity> {

    public ProjectModel(MainActivity activity) {
        super(activity);
    }

    public ProjectModel(Fragment fragment) {
        super(fragment);
    }
}
