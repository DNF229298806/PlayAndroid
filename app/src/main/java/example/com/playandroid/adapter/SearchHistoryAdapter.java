package example.com.playandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import example.com.playandroid.content.search.suggest.SearchHistoryEntity;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/18 16:29
 */
public class SearchHistoryAdapter extends BaseQuickAdapter<SearchHistoryEntity, BaseViewHolder> {
    public SearchHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHistoryEntity item) {

    }
}
