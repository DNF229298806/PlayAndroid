package example.com.playandroid.content.navigation;

import android.support.v4.app.Fragment;

import example.com.playandroid.base.BaseModel;
import example.com.playandroid.content.main.MainActivity;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:08
 */
public class NavigationModel extends BaseModel<MainActivity> {

    public NavigationModel(MainActivity activity) {
        super(activity);
    }

    public NavigationModel(Fragment fragment) {
        super(fragment);
    }
}
