package example.com.playandroid.network;

import java.util.List;

import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.content.home.net.PageEntity;
import example.com.playandroid.network.entity.InfoEntity;
import example.com.playandroid.network.entity.UserEntity;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 21:06
 */
public interface Api {
    String HOST = "http://www.wanandroid.com";

    @POST("/user/register")
    @FormUrlEncoded
        //Observable<InfoEntity> register(@Body RegisterParams params);
    Observable<InfoEntity<UserEntity>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

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
}
