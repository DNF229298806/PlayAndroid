package example.com.playandroid.content.webview;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import example.com.playandroid.base.BaseModel;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.databinding.ActivityWebviewBinding;
import example.com.playandroid.util.AnimUtil;

/**
 * @author admin
 * @des 2018/10/25
 */
public class WebViewModel extends BaseModel<WebViewActivity, ActivityWebviewBinding> {
    private String title = "玩安卓";
    private String link = "http://www.bilibili.com";

    public WebViewModel(WebViewActivity activity) {
        super(activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AnimUtil.initPopLayout(getBinding().fab, getBinding().backDrop, getBinding().llLink, getBinding().llBrowser, getBinding().llLike);
        link = getActivity().getIntent().getStringExtra(Constant.link);
        title = getActivity().getIntent().getStringExtra(Constant.article_title);
        getBinding().webView.loadUrl(link);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void navigationClick(View view) {
        getActivity().finish();
    }

    public void collectionClick(View view) {
        ToastUtils.showLong("收藏了");
        hideFAB();
    }

    public void browserClick(View view) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        getActivity().startActivity(intent);
        hideFAB();
    }

    private void hideFAB() {
        View[] views = {getBinding().llLink, getBinding().llBrowser, getBinding().llLike};
        AnimUtil.toggleFabMode(getBinding().fab, views, getBinding().backDrop);
    }
}
