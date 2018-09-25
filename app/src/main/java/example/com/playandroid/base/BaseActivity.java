package example.com.playandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 21:40
 */
public class BaseActivity extends AppCompatActivity {
    public ListCompositeDisposable list = new ListCompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.dispose();
    }

    public void addDisposable(Disposable disposable) {
        list.add(disposable);
    }
}
