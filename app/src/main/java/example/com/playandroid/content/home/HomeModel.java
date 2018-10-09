package example.com.playandroid.content.home;

import android.support.v4.app.Fragment;

import example.com.playandroid.base.BaseModel;
import example.com.playandroid.content.main.MainActivity;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 22:10
 */
public class HomeModel extends BaseModel<MainActivity> {
    private HomeFragment mFragment;

    public HomeModel(Fragment fragment) {
        super(fragment);
    }
}
