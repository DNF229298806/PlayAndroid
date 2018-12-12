package example.com.playandroid.content.navigation;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.com.playandroid.base.TestEntity;

/**
 * @author admin
 * @des 2018/12/12
 */
public class TestBRVAHadapter extends BaseQuickAdapter<TestEntity,BaseViewHolder> {
    public TestBRVAHadapter(int layoutResId, @Nullable List<TestEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestEntity item) {

    }
}
