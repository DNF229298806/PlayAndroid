package example.com.playandroid.content.navigation;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import example.com.playandroid.base.TestEntity;

/**
 * @author admin
 * @des 2018/12/12
 */
public class TestBRVAH2adapter extends BaseQuickAdapter<TestEntity,TestViewHolder> {
    public TestBRVAH2adapter(int layoutResId, @Nullable List<TestEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(TestViewHolder helper, TestEntity item) {

    }
}
