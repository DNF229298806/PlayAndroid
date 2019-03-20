package example.com.playandroid.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import example.com.playandroid.content.project.tabpage.TabViewPagerAdapterItem;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/20 10:11
 */
public class TabViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<TabViewPagerAdapterItem> itemList;

    public TabViewPagerAdapter(FragmentManager fm,List<TabViewPagerAdapterItem> itemList) {
        super(fm);
        this.itemList = itemList;
    }

    @Override
    public Fragment getItem(int position) {
        return itemList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itemList.get(position).getTitle();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
