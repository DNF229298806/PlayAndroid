package example.com.playandroid.content.navigation;


import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author admin
 * @des 2018/12/14
 */
public class Level0Item extends AbstractExpandableItem<Person> implements MultiItemEntity {
    public String title;
    public String subTitle;

    public Level0Item(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }
}
