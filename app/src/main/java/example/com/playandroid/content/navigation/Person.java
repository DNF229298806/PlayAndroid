package example.com.playandroid.content.navigation;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author admin
 * @des 2018/12/14
 */
public class Person implements MultiItemEntity {
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String name;
    public int age;

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_PERSON;
    }
}
