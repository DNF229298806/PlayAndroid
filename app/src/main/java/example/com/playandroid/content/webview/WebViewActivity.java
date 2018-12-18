package example.com.playandroid.content.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Route;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.databinding.ActivityWebviewBinding;
import example.com.playandroid.util.StatusBarUtil;

/**
 * @author admin
 * @des 2018/10/25
 */
@Route(path = Constant.ActivityPath.WebViewActivity)
public class WebViewActivity extends BaseActivity<WebViewModel, ActivityWebviewBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModel(new WebViewModel(this));
        setSupportActionBar(getBinding().toolbar);
        StatusBarUtil.setStatusBarColor(this, R.color.tool_bar_blue);
        initWebSettings();

    }

    @Override
    public int setLayout() {
        return R.layout.activity_webview;
    }

    private void initWebSettings() {
        WebSettings settings = getBinding().webView.getSettings();
        // 支持 Js 使用 也许会有XSS攻击
        settings.setJavaScriptEnabled(true);
        // 开启DOM缓存
        settings.setDomStorageEnabled(true);
      /*  // 开启数据库缓存
        settings.setDatabaseEnabled(true);*/
        // 支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        // 设置 WebView 的缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 支持启用缓存模式
        settings.setAppCacheEnabled(true);
       /* // 设置 AppCache 最大缓存值(现在官方已经不提倡使用，已废弃)
        settings.setAppCacheMaxSize(8 * 1024 * 1024);*/
        // Android 私有缓存存储，如果你不调用setAppCachePath方法，WebView将不会产生这个目录
        settings.setAppCachePath(getCacheDir().getAbsolutePath());
       /* // 数据库路径
        settings.setDatabasePath(getDatabasePath("html").getPath());*/
        // 关闭密码保存提醒功能
        settings.setSavePassword(false);
        // 支持缩放
        settings.setSupportZoom(true);
        // 设置 UserAgent 属性
        settings.setUserAgentString("");
        // 允许加载本地 html 文件/false
        settings.setAllowFileAccess(true);
        // 允许通过 file url 加载的 Javascript 读取其他的本地文件,Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
        settings.setAllowFileAccessFromFileURLs(false);
        // 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源，
        // Android 4.1 之前默认是true，在 Android 4.1 及以后默认是false,也就是禁止
        // 如果此设置是允许，则 setAllowFileAccessFromFileURLs 不起做用
        /**
         * 设置webview回调 载入Url的时候显示 载入完成的时候隐藏
         * 并实时刷新进度
         */
        getBinding().webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //view.loadUrl(request.getUrl().toString());
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                getBinding().progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                getBinding().progressBar.setVisibility(View.GONE);
                String title = view.getTitle();
                getBinding().toolbar.setTitle(title);
            }
        });

        getBinding().webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                getBinding().progressBar.setProgress(newProgress);
            }
        });
    }

    /**
     * 如果WebView还可以返回的话  就让它返回 直到根目录的时候 再进行退出
     *
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && getBinding().webView.canGoBack()) {
            getBinding().webView.goBack();
            return true;
        } else {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 清空网页访问留下的缓存数据。需要注意的时，由于缓存是全局的，
     * 所以只要是WebView用到的缓存都会被清空，即便其他地方也会使用到。
     * 该方法接受一个参数，从命名即可看出作用。若设为false，则只清空内存里的资源缓存，而不清空磁盘里的。
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebView wv = getBinding().webView;
        wv.clearCache(true);
        wv.clearHistory();// 清除当前 WebView 访问的历史记录
        wv.removeAllViews();
        wv.destroy();
    }
}
