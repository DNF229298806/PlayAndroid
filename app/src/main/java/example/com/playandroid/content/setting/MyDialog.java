package example.com.playandroid.content.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.R;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/14 9:22
 */
public class MyDialog extends AlertDialog {

    public MyDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_my);

        TextView yes = findViewById(R.id.tv_yes);
        TextView no = findViewById(R.id.tv_no);
        TextView cancel = findViewById(R.id.tv_cancel);
        yes.setOnClickListener(v -> {
            ToastUtils.showLong("测试成功");
            dismiss();
        });
        no.setOnClickListener(v -> {
            ToastUtils.showLong("取消了");
            dismiss();
        });
        cancel.setOnClickListener(v -> {
            ToastUtils.showLong("点了XX");
            dismiss();
        });
    }
}
