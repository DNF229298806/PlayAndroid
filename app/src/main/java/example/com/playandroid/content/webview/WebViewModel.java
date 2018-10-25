package example.com.playandroid.content.webview;

import android.view.View;

import example.com.playandroid.base.BaseModel;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.databinding.ActivityWebviewBinding;

/**
 * @author admin
 * @des 2018/10/25
 */
public class WebViewModel extends BaseModel<WebViewActivity,ActivityWebviewBinding> {
    private String title="玩安卓";
    public WebViewModel(WebViewActivity activity) {
        super(activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String link = getActivity().getIntent().getStringExtra(Constant.link);
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

}
