package example.com.playandroid.network;

import java.util.List;

import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.network.entity.InfoEntity;
import example.com.playandroid.network.entity.UserEntity;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
