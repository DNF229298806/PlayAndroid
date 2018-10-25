package example.com.playandroid.base;

import android.arch.lifecycle.LifecycleObserver;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/14 14:42
 */
public class BaseFragmentModel<T extends BaseActivity, V extends BaseFragment,K extends ViewDataBinding>extends Model implements LifecycleObserver {
    private T mActivity;
    private V mFragment;
    private K mBinding;
    public ListCompositeDisposable list = new ListCompositeDisposable();

    public void addDisposable(Disposable disposable) {
        list.add(disposable);
    }

    public BaseFragmentModel(T activity, V fragment) {
        mActivity = activity;
        mFragment = fragment;
        mBinding = (K) fragment.getBinding();
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

    public K getBinding() {
        return mBinding;
    }

/*@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onFragmentCreate() {
        Timber.e("WishScoreRelativeLayout.ON_CREATE()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onFragmentStart() {
        Timber.e("WishScoreRelativeLayout.ON_START()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onFragmentResume() {
        Timber.e("WishScoreRelativeLayout.ON_RESUME()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onFragmentPause() {
        Timber.e("WishScoreRelativeLayout.ON_PAUSE()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onFragmentStop() {
        Timber.e("WishScoreRelativeLayout.ON_STOP()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onFragmentDestroy() {
        list.dispose();
        Timber.e("WishScoreRelativeLayout.ON_DESTROY()");
        mFragment.getLifecycle().removeObserver(this);
    }*/

    @Override
    public void onCreate() {
        Timber.e("WishScoreRelativeLayout.ON_CREATE()");
    }

    @Override
    public void onStart() {
        Timber.e("WishScoreRelativeLayout.ON_START()");
    }

    @Override
    public void onResume() {
        Timber.e("WishScoreRelativeLayout.ON_RESUME()");
    }

    @Override
    public void onPause() {
        Timber.e("WishScoreRelativeLayout.ON_PAUSE()");
    }

    @Override
    public void onStop() {
        Timber.e("WishScoreRelativeLayout.ON_STOP()");
    }

    @Override
    @CallSuper
    public void onDestroy() {
        list.dispose();
        Timber.e("WishScoreRelativeLayout.ON_DESTROY()");
        mFragment.getLifecycle().removeObserver(this);
    }
}
