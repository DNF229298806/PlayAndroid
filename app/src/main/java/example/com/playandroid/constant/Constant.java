package example.com.playandroid.constant;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 21:15
 */
public interface Constant {
    String link = "link";
    String article_title = "ARTICLE_TITLE";
    String user_entity = "user";
    String app_theme = "AppTheme";
    String article_id = "ARTICLE_ID";
    int REQUEST_CODE_CHOOSE = 23;//定义请求码常量
    String id = "id";
    String class_title = "class_title";

    interface TitleType{
        int TYPE_LEVEL_0 = 0;
        int TYPE_PERSON = 1;
    }

    interface NetWork {
        String DEVICE = "android";
        String APP_ID = "3";
        int success = 1;
        int start = 0;
        int tokenExpire = 51;
        int logout = 50;
    }

    interface FragmentType {
        int HOME = 0;
        int PROJECT = 1;
        int SYSTEM = 2;
        int NAVIGATION = 3;
    }

    interface ActivityPath {
        String HomeActivity = "/home/";
        String LoginActivity = "/login/";
        String WebViewActivity = HomeActivity + "webview";
        String MainActivity = HomeActivity + "main";
        String OpenApiActivity = HomeActivity + "openapi";
        String TestActivity = HomeActivity + "test";
        String TestActivity2 = HomeActivity + "test2";
        String TestActivity3 = HomeActivity + "test3";
        String CollectionActivity = HomeActivity + "collection";
        String SettingActivity = HomeActivity + "setting";
        String SkinChooseActivity = HomeActivity + "SkinChooseActivity";
        String SystemArticleActivity = HomeActivity + "SystemArticleActivity";
    }
}
