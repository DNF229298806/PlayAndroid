package example.com.playandroid.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/21 11:47
 */
public abstract class Model implements LifecycleObserver {
    public ListCompositeDisposable list = new ListCompositeDisposable();
    public void addDisposable(Disposable disposable) {
        list.add(disposable);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public abstract void onCreate();

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public abstract void onStart();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public abstract void onResume();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public abstract void onPause();

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public abstract void onStop();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public abstract void onDestroy();
}
