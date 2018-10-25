package example.com.playandroid.content.home;

import android.content.Context;

import java.util.List;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseBindingAdapter;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.databinding.HolderArticleItemBinding;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/20 0:17
 */
public class ArticleBindingAdapter extends BaseBindingAdapter<ArticleEntity,HolderArticleItemBinding> {


    public ArticleBindingAdapter(List<ArticleEntity> data, Context context) {
        super(data, context);
    }

    @Override
    public int setLayout() {
        return R.layout.holder_article_item;
    }

}
