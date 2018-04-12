package com.wen_wen.latte.app.net;

import com.wen_wen.latte.app.app.ConfigType;
import com.wen_wen.latte.app.app.Latte;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by WeLot on 2018/4/12.
 */

public class RestCreator {

    public static RestfulService getRestService() {

        return RestServiceHolder.REST_SERVICE;
    }


    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigurations()
                .get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                //转换器  转化为String
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHolder {

        private static final int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestfulService REST_SERVICE = RetrofitHolder
                .RETROFIT_CLIENT.create(RestfulService.class);
    }


}
