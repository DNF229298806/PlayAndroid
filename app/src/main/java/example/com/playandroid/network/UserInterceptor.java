package example.com.playandroid.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.IOException;
import java.util.HashMap;

import example.com.playandroid.BuildConfig;
import example.com.playandroid.constant.Constant;
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
        SPUtils sp = SPUtils.getInstance(Constant.user_entity);
        if (isConnection) {
            String token_pass = TextUtils.isEmpty(sp.getString("token_pass")) ? "" : sp.getString("token_pass");
            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .addHeader("token_pass", token_pass)
                    //                    .addHeader("appid", Constant.NetWork.appId)
                    //                    .addHeader("nonceStr",nonceStr)
                    //                    .addHeader("sign",sign)
                    //                    .addHeader("accessToken", User.getToken_pass())
                    //                    .addHeader("device",Constant.NetWork.device)
                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .addHeader("Cookie","loginUserName="+sp.getString("username"))
                    .addHeader("Cookie", "loginUserPassword="+sp.getString("password"))
                    .build();
        } else {
            //设置缓存
            ToastUtils.showShort("无网络！请稍后重试");
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
        String token = response.headers().get("Set-Cookie");
        if (!TextUtils.isEmpty(token)) {
            sp.put("token_pass",token);
        }
        return response;
    }
}
