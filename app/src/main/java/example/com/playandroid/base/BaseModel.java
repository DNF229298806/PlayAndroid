package example.com.playandroid.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/30 17:12
 */
public class BaseModel<T extends BaseActivity> implements LifecycleObserver {
    private T mActivity;

    public ListCompositeDisposable list = new ListCompositeDisposable();

    public void addDisposable(Disposable disposable) {
        list.add(disposable);
    }
    public BaseModel(T activity) {
        mActivity = activity;
        mActivity.getLifecycle().addObserver(this);
    }

    public T getActivity() {
        return mActivity;
    }

    public void setActivity(T activity) {
        mActivity = activity;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onActivityCreate() {
        Timber.e("WishScoreRelativeLayout.ON_CREATE()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onActivityStart() {
        Timber.e("WishScoreRelativeLayout.ON_START()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onActivityResume() {
        Timber.e("WishScoreRelativeLayout.ON_RESUME()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onActivityPause() {
        Timber.e("WishScoreRelativeLayout.ON_PAUSE()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onActivityStop() {
        Timber.e("WishScoreRelativeLayout.ON_STOP()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onActivityDestroy() {
        mActivity.getLifecycle().removeObserver(this);
        list.dispose();
        Timber.e("WishScoreRelativeLayout.ON_DESTROY()");
    }
    /*private Fragment mFragment;*/

    /* public BaseModel() {
         mActivity.getLifecycle().addObserver(this);
     }*/

    /*public BaseModel(Fragment fragment) {
        mFragment = fragment;
    }*/
    /*  public Fragment getFragment() {
          return mFragment;
      }

      public void setFragment(Fragment fragment) {
          mFragment = fragment;
      }*/
}
