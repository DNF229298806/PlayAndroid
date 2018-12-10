package example.com.playandroid;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import timber.log.Timber;

/**
 * @author admin
 * @des 2018/12/6
 */
public class Demo {
    public static void main(String[] args) {

                /**
                 * Groupby操作符 不用莱姆大表达式
                 */
        Disposable subscribe = Observable.just(2, 3, 4, 5, 6).groupBy(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                if (integer % 2 == 0) {
                    return "偶数";
                } else {
                    return "奇数";
                }
            }
        }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(GroupedObservable<String, Integer> ob) throws Exception {
                String key = ob.getKey();
                if (key.equals("偶数")) {
                    Disposable subscribe1 = ob.subscribe(i -> Timber.i("AAAA" + key + i), Throwable::printStackTrace);
                } else if (key.equals("奇数")) {
                    Disposable subscribe1 = ob.subscribe(i -> Timber.i("BBBB" + key + i), Throwable::printStackTrace);
                }
            }
        });

        /**
         * 使用lambda表达式
         */
        Disposable subscribe2 = Observable.just(2, 3, 4, 5, 6).groupBy(integer -> {
            if (integer % 2 == 0) {
                return "偶数";
            } else {
                return "奇数";
            }
        }).subscribe(ob -> {
            String key = ob.getKey();
            if (key.equals("偶数")) {
                Disposable subscribe1 = ob.subscribe(i -> Timber.i("AAAA" + key + i), Throwable::printStackTrace);
            } else if (key.equals("奇数")) {
                Disposable subscribe1 = ob.subscribe(i -> Timber.i("BBBB" + key + i), Throwable::printStackTrace);
            }
        });
    }
}
