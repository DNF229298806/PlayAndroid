package example.com.playandroid.content.navigation;

import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
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

    public void onClick(View view) {
        ToastUtils.showLong("北京申奥成功了");
    }
}
