package example.com.playandroid.content.openapi;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import static example.com.playandroid.constant.Constant.TitleType.TYPE_LEVEL_0;

/**
 * @author admin
 * @des 2018/12/21
 */
public class OpenApiEntity extends AbstractExpandableItem<ContentEntity> implements MultiItemEntity {
    private String title;
    private List<ContentEntity> list;

    public OpenApiEntity() {
    }

    public OpenApiEntity(String title, List<ContentEntity> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ContentEntity> getList() {
        return list;
    }

    public void setList(List<ContentEntity> list) {
        this.list = list;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return TYPE_LEVEL_0;
    }
}
