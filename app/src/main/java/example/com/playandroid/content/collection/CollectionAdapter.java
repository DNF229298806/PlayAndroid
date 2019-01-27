package example.com.playandroid.content.collection;

import android.support.annotation.Nullable;

import java.util.List;

import example.com.playandroid.base.brvah.BaseDataBindingAdapter;
import example.com.playandroid.content.main.CollectionArticleEntity;
import example.com.playandroid.databinding.HolderCollectionItemBinding;

/**
 * @author Richard_Y_Wang
 * @des 2019/1/27 15:30
 */
public class CollectionAdapter extends BaseDataBindingAdapter<CollectionArticleEntity,HolderCollectionItemBinding> {
    public CollectionAdapter(int layoutResId, @Nullable List<CollectionArticleEntity> data) {
        super(layoutResId, data);
    }

    public CollectionAdapter(@Nullable List<CollectionArticleEntity> data) {
        super(data);
    }

    public CollectionAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(HolderCollectionItemBinding binding, CollectionArticleEntity item) {
        binding.setEntity(item);
    }


}
