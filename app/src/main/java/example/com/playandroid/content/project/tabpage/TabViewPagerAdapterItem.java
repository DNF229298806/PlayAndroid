package example.com.playandroid.content.project.tabpage;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.content.system.SystemEntity;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/20 10:12
 */
public class TabViewPagerAdapterItem {
    private String title;
    private Fragment fragment;

    public TabViewPagerAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public static List<TabViewPagerAdapterItem> createProjectTabFragments(List<SystemEntity> list) {
        List<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (SystemEntity systemEntity : list) {
            adapterItems.add(new TabViewPagerAdapterItem(systemEntity.getName(), ProjectTabPageFragment.newInstance(systemEntity.getId())));
        }
        return adapterItems;
    }
}
