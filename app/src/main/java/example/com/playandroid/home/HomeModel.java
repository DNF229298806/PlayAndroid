package example.com.playandroid.home;

import android.content.res.Resources;

import example.com.playandroid.network.Api;
import example.com.playandroid.network.ApiUtils;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 22:10
 */
public class HomeModel {
    private Api api;
    private HomeActivity mActivity;
    private Resources mResources;

    public HomeModel(HomeActivity activity, Resources resources) {
        mActivity = activity;
        mResources = resources;
        api = ApiUtils.INSTANCE.getApi(activity);
    }

}
