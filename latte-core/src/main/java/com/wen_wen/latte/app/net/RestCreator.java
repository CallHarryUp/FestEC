package com.wen_wen.latte.app.net;

import com.wen_wen.latte.app.app.ConfigKeys;
import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by WeLot on 2018/4/12.
 */

public class RestCreator {


    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    /**
     * 构建全局retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfiguration(ConfigKeys.API_HOST);

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                //转换器  转化为String
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHolder {

        private static final int TIME_OUT = 60;
        //创建拦截器builder
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        //获取拦截器集合
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        //添加拦截器方法
        private static OkHttpClient.Builder addInterceptor() {
            for (Interceptor interceptor : INTERCEPTORS) {
                BUILDER.addInterceptor(interceptor);
            }

            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder
                .RETROFIT_CLIENT.create(RestService.class);
    }
    public static RestService getRestService() {

        return RestServiceHolder.REST_SERVICE;
    }


    /**
     * Service接口(Rx)
     */
    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE = RetrofitHolder
                .RETROFIT_CLIENT.create(RxRestService.class);
    }
    public static RxRestService getRxRestService() {

        return RxRestServiceHolder.REST_SERVICE;
    }




}
