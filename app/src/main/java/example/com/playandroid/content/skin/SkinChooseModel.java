package example.com.playandroid.content.skin;

import android.view.View;

import example.com.playandroid.base.BaseModel;
import example.com.playandroid.databinding.ActivityChooseSkinBinding;
import skin.support.SkinCompatManager;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/15 16:51
 */
public class SkinChooseModel extends BaseModel<SkinChooseActivity, ActivityChooseSkinBinding> {

    public SkinChooseModel(SkinChooseActivity activity) {
        super(activity);
    }

    public void navigationClick(View view) {
        getActivity().finish();
    }

    public void onRedClick(View view) {
        SkinCompatManager.getInstance().loadSkin("red.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
    }

    public void onGreenClick(View view) {
        SkinCompatManager.getInstance().loadSkin("green", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
    }

    public void onBlueClick(View view) {
        //恢复应用默认皮肤
        SkinCompatManager.getInstance().restoreDefaultTheme();
    }
}
