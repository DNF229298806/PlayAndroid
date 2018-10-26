package example.com.playandroid.base;

import android.databinding.ViewDataBinding;

import example.com.playandroid.R;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/14 21:25
 */
public class TestEntity extends BaseEntity{
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getViewType() {
        return R.layout.test_item;
    }

    @Override
    public ViewDataBinding getDataBinding() {
        return null;
    }

    @Override
    public void setDataBinding(ViewDataBinding viewDataBinding) {

    }
}
