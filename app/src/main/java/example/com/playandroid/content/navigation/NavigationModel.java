package example.com.playandroid.content.navigation;

import example.com.playandroid.base.BaseFragmentModel;
import example.com.playandroid.content.main.MainActivity;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:08
 */
public class NavigationModel extends BaseFragmentModel<MainActivity,NavigationFragment> {

    public NavigationModel() {
    }

    public NavigationModel(MainActivity activity, NavigationFragment fragment) {
        super(activity, fragment);
    }
}
