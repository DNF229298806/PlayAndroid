package example.com.playandroid.network.transform;


import example.com.playandroid.network.entity.InfoEntity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/9/2
 * @updateAuthor $Author$
 */
public class RestfulTransformer<T> implements ObservableTransformer<InfoEntity<T>, T> {
    private InfoEntity<T> entity;

    @Override
    public ObservableSource<T> apply(Observable<InfoEntity<T>> upstream) {
        return upstream
                .compose(new ErrorTransform<>())
                .flatMap(this::flat);
    }

    private ObservableSource<? extends T> flat(InfoEntity<T> tInfoEntity) {
        this.entity = tInfoEntity;
        return Observable.create(this::create);
    }


    //todo 明天回来 看方法的调用过程 打断点 看下到底是怎么走的
    private void create(ObservableEmitter<T> emitter) {
        try {
            if (entity.getData() != null)
                emitter.onNext(entity.getData());
        } catch (Exception e) {
            emitter.onError(e);
        } finally {
            emitter.onComplete();
        }
    }
}
