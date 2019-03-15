package example.com.playandroid.content.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import example.com.playandroid.R;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/15 10:47
 */
public class UpdateDialog extends AlertDialog {
    protected UpdateDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update);
    }
}
