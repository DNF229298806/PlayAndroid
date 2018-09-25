package example.com.playandroid.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;
import java.util.HashMap;

import example.com.playandroid.BuildConfig;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp 拦截器
 * @author Richard_Y_Wang
 * @des 2018/9/25 21:00
 */
public class UserInterceptor implements Interceptor{
    private Context context;

    public UserInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        //boolean isConnection = BaseUtil.isNetworkConnected(context);
        boolean isConnection = NetworkUtils.isConnected();
        String nonceStr = String.valueOf(System.currentTimeMillis());
        //签名
        //todo 查一下这个BuildConfig
        String sign = "";
        HashMap<String, String> map = new HashMap<>();
        //看情况写 选择参数
        //map.put("appId", Constant.NetWork.APP_ID);
        //map.put("device", Constant.NetWork.DEVICE);
        map.put("nonceStr",nonceStr);
        map.put("version", BuildConfig.VERSION_NAME);
        //加签名 用的是支付宝的
        /*try {
            sign =  AlipaySignature.rsaSign(AlipaySignature.getSignContent(map),Constant.privateKey,"utf-8","RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }*/

        //看需求进行加请求头的操作 第一次进来的时候首先移除请求头
        if (isConnection) {
            request = request.newBuilder()
                    .removeHeader("Pragma")
                    //                    .addHeader("User-Agent", "android")
                    //                    .addHeader("appid", Constant.NetWork.appId)
                    //                    .addHeader("nonceStr",nonceStr)
                    //                    .addHeader("sign",sign)
                    //                    .addHeader("accessToken", User.getToken())
                    //                    .addHeader("device",Constant.NetWork.device)
                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .build();
        } else {
            //设置缓存
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
           /* request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .url(chain.request().url())
                    //.addHeader("User-Agent", "android")
                    .build();*/
        }

        //不知道这里在干嘛
        Response response = chain.proceed(request);
        if(response.code() == 401){
            return response.newBuilder().code(401).message("").build();
        }
        return response;
    }
}
