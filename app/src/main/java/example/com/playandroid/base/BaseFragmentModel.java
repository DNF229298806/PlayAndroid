package example.com.playandroid.base;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/14 14:42
 */
public class BaseFragmentModel<T extends BaseActivity,V extends BaseFragment> {
    private T mActivity;
    private V mFragment;

    public BaseFragmentModel() {

    }

    public BaseFragmentModel(T activity) {
        mActivity = activity;
    }

    public BaseFragmentModel(T activity, V fragment) {
        mActivity = activity;
        mFragment = fragment;
    }

    public T getActivity() {
        return mActivity;
    }

    public void setActivity(T activity) {
        mActivity = activity;
    }

    public V getFragment() {
        return mFragment;
    }

    public void setFragment(V fragment) {
        mFragment = fragment;
    }
}
