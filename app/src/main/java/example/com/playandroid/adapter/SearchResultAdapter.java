package example.com.playandroid.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import example.com.playandroid.R;
import example.com.playandroid.content.home.net.ArticleEntity;
import example.com.playandroid.util.DogUtil;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/19 9:12
 */
public class SearchResultAdapter extends BaseQuickAdapter<ArticleEntity, BaseViewHolder> {

    private Context context;

    public SearchResultAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleEntity item) {
        helper.setText(R.id.super_chapter_name, item.getSuperChapterName())
                .setText(R.id.chapter_name, item.getChapterName())
                .setText(R.id.title, DogUtil.delHtmlTags(item.getTitle()))
                .setText(R.id.desc, item.getDesc())
                .setText(R.id.author, item.getAuthor())
                .setText(R.id.pub_date, item.getNiceDate());
        ImageView iv = helper.getView(R.id.envelope_pic);
        if (TextUtils.isEmpty(item.getEnvelopePic())) {
            iv.setVisibility(View.GONE);
        } else {
            iv.setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getEnvelopePic()).into(iv);
        }
        //String time = TimeUtils.millis2String(item.getPublishTime(), new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()));
    }
}
