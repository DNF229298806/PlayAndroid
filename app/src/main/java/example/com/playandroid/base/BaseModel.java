package example.com.playandroid.base;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/30 17:12
 */
public class BaseModel<T extends BaseActivity> {
    private T mActivity;

    public BaseModel(T activity) {
        mActivity = activity;
    }

    public T getActivity() {
        return mActivity;
    }

    public void setActivity(T activity) {
        mActivity = activity;
    }
}
