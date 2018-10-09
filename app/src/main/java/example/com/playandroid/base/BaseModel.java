package example.com.playandroid.base;

import android.support.v4.app.Fragment;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/30 17:12
 */
public class BaseModel<T extends BaseActivity> {
    private T mActivity;
    private Fragment mFragment;

    public BaseModel(T activity) {
        mActivity = activity;
    }

    public BaseModel(Fragment fragment) {
        mFragment = fragment;
    }

    public T getActivity() {
        return mActivity;
    }

    public void setActivity(T activity) {
        mActivity = activity;
    }
}
