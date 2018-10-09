package example.com.playandroid.content.system;

import android.support.v4.app.Fragment;

import example.com.playandroid.base.BaseModel;
import example.com.playandroid.content.main.MainActivity;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/9 21:08
 */
public class SystemModel extends BaseModel<MainActivity> {

    public SystemModel(MainActivity activity) {
        super(activity);
    }

    public SystemModel(Fragment fragment) {
        super(fragment);
    }
}
