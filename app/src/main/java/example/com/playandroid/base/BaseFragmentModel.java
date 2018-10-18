package example.com.playandroid.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/14 14:42
 */
public class BaseFragmentModel<T extends BaseActivity,V extends BaseFragment> implements LifecycleObserver {
    private T mActivity;
    private V mFragment;

  /*  public BaseFragmentModel() {
        mFragment.getLifecycle().addObserver(this);
    }

    public BaseFragmentModel(T activity) {
        mActivity = activity;
        mFragment.getLifecycle().addObserver(this);
    }*/

    public BaseFragmentModel(T activity, V fragment) {
        mActivity = activity;
        mFragment = fragment;
        mFragment.getLifecycle().addObserver(this);
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

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onFragmentCreate() {
        Timber.e( "WishScoreRelativeLayout.ON_CREATE()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onFragmentStart() {
        Timber.e( "WishScoreRelativeLayout.ON_START()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onFragmentResume() {
        Timber.e( "WishScoreRelativeLayout.ON_RESUME()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onFragmentPause() {
        Timber.e( "WishScoreRelativeLayout.ON_PAUSE()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onFragmentStop() {
        Timber.e("WishScoreRelativeLayout.ON_STOP()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onFragmentDestroy() {
        Timber.e( "WishScoreRelativeLayout.ON_DESTROY()");
    }
}
