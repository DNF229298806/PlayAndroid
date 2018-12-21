package example.com.playandroid.content.openapi;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import example.com.playandroid.constant.Constant;
import example.com.playandroid.util.ArouterUtil;

import static example.com.playandroid.constant.Constant.TitleType.TYPE_PERSON;

/**
 * @author admin
 * @des 2018/12/21
 */
public class ContentEntity implements MultiItemEntity {
    private String name;
    private String link;

    public ContentEntity() {
    }

    public ContentEntity(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.link, link);
        bundle.putString(Constant.article_title,name);
        ArouterUtil.navigation(Constant.ActivityPath.WebViewActivity,bundle);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int getItemType() {
        return TYPE_PERSON;
    }
}
