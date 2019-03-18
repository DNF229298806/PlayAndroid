package example.com.playandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import example.com.playandroid.R;
import example.com.playandroid.content.search.suggest.HotKeyEntity;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/18 16:24
 */
public class HotKeyAdapter extends BaseQuickAdapter<HotKeyEntity, BaseViewHolder> {


    public HotKeyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotKeyEntity item) {
        helper.setText(R.id.tag, item.getName());
    }
}
