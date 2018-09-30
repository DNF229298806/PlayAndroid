package example.com.playandroid.content.home;

import android.content.res.Resources;

import example.com.playandroid.network.Api;
import example.com.playandroid.network.ApiUtils;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 22:10
 */
public class HomeModel {
    private Api api;
    private HomeFragment mFragment;
    private Resources mResources;

    public HomeModel(HomeFragment fragment, Resources resources) {
        mFragment = fragment;
        mResources = resources;
        api = ApiUtils.INSTANCE.getApi(fragment.getActivity());
    }

}
