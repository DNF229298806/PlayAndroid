package example.com.playandroid.base;

import android.databinding.ViewDataBinding;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import timber.log.Timber;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/30 17:12
 */
public class BaseModel<T extends BaseActivity,V extends ViewDataBinding> extends Model{
    private T mActivity;
    private V mBinding;
    public ListCompositeDisposable list = new ListCompositeDisposable();

    public void addDisposable(Disposable disposable) {
        list.add(disposable);
    }

    public BaseModel(T activity) {
        mActivity = activity;
        mBinding = (V) activity.getBinding();
        mActivity.getLifecycle().addObserver(this);
    }

    public T getActivity() {
        return mActivity;
    }

    public void setActivity(T activity) {
        mActivity = activity;
    }

    public V getBinding() {
        return mBinding;
    }


   /*@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
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
        list.dispose();
        Timber.e("WishScoreRelativeLayout.ON_DESTROY()");
        mActivity.getLifecycle().removeObserver(this);
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
    public void onDestroy() {
        list.dispose();
        Timber.e("WishScoreRelativeLayout.ON_DESTROY()");
        mActivity.getLifecycle().removeObserver(this);
    }
}
