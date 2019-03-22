package example.com.playandroid.content.home;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.playandroid.App;
import example.com.playandroid.R;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/21 15:36
 */
public class RewardDialog extends Dialog {
    @BindView(R.id.zhifubao)
    TextView zhifubao;
    @BindView(R.id.wx)
    TextView wx;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.bt_submit)
    Button btSubmit;

    private boolean isZhiFuBaoChoose = false;
    private boolean isWxChoose = false;

    public RewardDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_reward);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.zhifubao, R.id.wx, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhifubao:
                isZhiFuBaoChoose = !isZhiFuBaoChoose;
                if (isZhiFuBaoChoose) {
                    isWxChoose = false;
                    wx.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                            null);
                }
                zhifubao.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        isZhiFuBaoChoose ? App.getResDrawable(R.mipmap.ic_right) : null,
                        null);
                break;
            case R.id.wx:
                isWxChoose = !isWxChoose;
                if (isWxChoose) {
                    isZhiFuBaoChoose = false;
                    zhifubao.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                            null);
                }
                wx.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        isWxChoose ? App.getResDrawable(R.mipmap.ic_right) : null,
                        null);
                break;
            case R.id.bt_submit:
                if (checkValid()) {
                    dismiss();
                }
                break;
        }
    }

    private boolean checkValid() {
        String money = etMoney.getText().toString();
        if (TextUtils.isEmpty(money)) {
            ToastUtils.showLong("请输入有效的金额");
            return false;
        } else if (!isZhiFuBaoChoose && !isWxChoose) {
            ToastUtils.showLong("请选择一种支付方式");
            return false;
        }
        return true;
    }
}
