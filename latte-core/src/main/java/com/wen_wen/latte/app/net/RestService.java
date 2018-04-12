package com.wen_wen.latte.app.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by WeLot on 2018/4/12.
 * 请求的通用封装
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @POST
    @FormUrlEncoded
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    @PUT
    @FormUrlEncoded
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);


    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    //download方式 是将文件下载到内存 再保存到文件中，可能会造成内存溢出
    // 加Streaming 注解   一边下载 一边保存
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    @Multipart
    @POST
    Call<String> post(@Url String url, @Part MultipartBody.Part file);

}
