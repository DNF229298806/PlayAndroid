package example.com.playandroid.network;

import java.util.List;

import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.content.home.net.PageEntity;
import example.com.playandroid.content.main.CollectionPageEntity;
import example.com.playandroid.content.navigation.NavigationTitleEntity;
import example.com.playandroid.content.register.UserEntity;
import example.com.playandroid.content.search.suggest.HotKeyEntity;
import example.com.playandroid.content.system.SystemEntity;
import example.com.playandroid.network.entity.InfoEntity;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 21:06
 */
public interface Api {
    String HOST = "https://www.wanandroid.com";
    /**
     * 公开的API
     */
    String OPEN_API = "/openapis";

    @POST("user/register")
    @FormUrlEncoded
    Observable<InfoEntity<UserEntity>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 登陆数据
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<InfoEntity<UserEntity>> login(@Field("username") String username, @Field("password") String password);

    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    Observable<InfoEntity<List<BannerEntity>>> getBannerEntity();

    /**
     * 获取首页文章列表
     *
     * @param num 页数
     * @return 首页文章列表数据
     */
    @GET("article/list/{num}/json")
    Observable<InfoEntity<PageEntity>> getFeedArticleList(@Path("num") int num);

    /**
     * 获取体系列表
     *
     * @return 体系列表json
     */
    @GET("tree/json")
    Observable<InfoEntity<List<SystemEntity>>> getSystemList();

    /**
     * 获取体系分类下文章数据
     *
     * @param page 页码
     * @param cid  id
     * @return 体系分类下文章数据
     */
    @GET("article/list/{page}/json")
    Observable<InfoEntity<PageEntity>> getHierarchyArticles(@Path("page") int page, @Query("cid") int cid);

    /**
     * 获取项目分类
     * @return 项目分类
     */
    @GET("project/tree/json")
    Observable<InfoEntity<List<SystemEntity>>> getProjectCategories();

    /**
     * 获取项目分类下文章数据
     * @param page 页码
     * @param cid id
     * @return  项目下的文章数据
     */
    @GET("project/list/{page}/json")
    Observable<InfoEntity<PageEntity>> getProjectArticles(@Path("page") int page, @Query("cid") int cid);

    /**
     * 获取导航数据
     */
    @GET("navi/json")
    Observable<InfoEntity<List<NavigationTitleEntity>>> getNavigationList();

    /**
     * 公开的API
     */
    @GET("openapis")
    Observable<String> getOpenAPIS();

    /**
     * 收藏站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     *
     * @param id article id
     * @return 收藏站内文章数据
     */
    @POST("lg/collect/{id}/json")
    Observable<InfoEntity> addCollectArticle(@Path("id") int id);

    /**
     * 获取收藏列表
     * http://www.wanandroid.com/lg/collect/list/0/json
     *
     * @param page page number
     * @return 收藏列表数据
     */
    @GET("lg/collect/list/{page}/json")
    Observable<InfoEntity<CollectionPageEntity>> getCollectList(@Path("page") int page);

    /**
     * 搜索热词
     *
     * @return 热词列表
     */
    @GET("hotkey/json")
    Observable<InfoEntity<List<HotKeyEntity>>> getHotKeyEntity();

    /**
     * @param page    页码
     * @param keyword 搜索关键词
     * @return 搜索结果
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<InfoEntity<PageEntity>> searchArticles(
            @Path("page") int page, @Field("k") String keyword
    );
}
