package example.com.playandroid.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import example.com.playandroid.BuildConfig;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/25 21:00
 */
public enum ApiUtils {
    INSTANCE;

    public Api getApi(Context context) {
        UserInterceptor userInterceptor = new UserInterceptor(context);
        OkHttpClient client = provideOkHttpClient().newBuilder()
                .addInterceptor(userInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(Api.HOST)
                //使用Gson作为数据转换器
                .addConverterFactory(GsonConverterFactory.create())
                //使用RxJava2作为回调适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                //添加网络访问工具OKHttpClient
                .callFactory(client)
                .build()
                .create(Api.class);
    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                //超时时间
                .readTimeout(30, TimeUnit.SECONDS)
                // HTTP过滤器 方便日志打印查看
                .addInterceptor(new HttpLoggingInterceptor())
                //                .cache(new Cache(new File(""),1024*1024*20))
                .build().newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        return httpClientBuilder.build();
    }
}
