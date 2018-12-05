package example.com.playandroid.base;

import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author admin
 * @des 2018/12/5
 */
public class ModelObserver<T> implements Observer<T> {
    private final Consumer<T> consumer;
    private final BaseModel model;

    public ModelObserver(BaseModel model,Consumer<T> consumer) {
        this.consumer = consumer;
        this.model = model;
    }


    @Override
    public void onSubscribe(Disposable d) {
        model.addDisposable(d);
    }

    @Override
    public void onNext(T t) {
        try {
            consumer.accept(t);
        } catch (Exception e) {
            ToastUtils.showLong(e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showLong(e.getMessage());
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
