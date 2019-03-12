package example.com.playandroid.content.main;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.com.playandroid.GlideApp;
import example.com.playandroid.R;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/12 16:09
 */
public class MyTestAdapter extends BaseQuickAdapter<TestItem, BaseViewHolder> {

    public MyTestAdapter(int layoutResId, @Nullable List<TestItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestItem item) {
        helper.setText(R.id.item_tv, item.getName());
        helper.setText(R.id.item_bt, item.getAge()+""   );
        GlideApp.with(mContext).load(item.getPitcure()).error(R.mipmap.empty_icon).into((ImageView) helper.getView(R.id.item_iv));
        helper.addOnClickListener(R.id.item_bt);
        helper.addOnClickListener(R.id.item_tv);
        helper.addOnClickListener(R.id.item_iv);
    }
}
