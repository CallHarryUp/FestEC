package com.wen_wen.latte.app.net;

/**
 * Created by WeLot on 2018/4/12.
 */

import android.content.Context;

import com.wen_wen.latte.app.download.DownloadHanlder;
import com.wen_wen.latte.app.net.callback.IError;
import com.wen_wen.latte.app.net.callback.IFailure;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.net.callback.Irequest;
import com.wen_wen.latte.app.net.callback.RequestCallbacks;
import com.wen_wen.latte.app.ui.LatteLoader;
import com.wen_wen.latte.app.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 设计模式：
 * 1、使用建造者模式
 * 2、 请求时加入loader
 * 3、完善请求方式
 * 4、完善download下载文件方式
 */
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final Irequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private File FILE;


    public RestClient(String url, Map<String, Object> params,
                      Irequest request,
                      String downloadDir,
                      String extension,
                      String name,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle

    ) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    //创建建造者
    public static RestClientBuilder builder() {

        return new RestClientBuilder();
    }

    //创建请求方式
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        //在请求开始是 调用loader  展示
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAM:
                call = service.putRaw(URL, BODY);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;

            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD://使用retrofit的方法
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);

                final MultipartBody.Part body = MultipartBody.Part.
                        createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    //获取实例
    private Callback<String> getRequestCallback() {

        return new RequestCallbacks(REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE);
    }

    //创建请求方法
    public final void get() {

        request(HttpMethod.GET);
    }

    //对post进行判断  是否请求原始数据
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params  must be null!");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAM);
        }


    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public void download() {
        new DownloadHanlder(URL,
                REQUEST,
                DOWNLOAD_DIR,
                EXTENSION,
                NAME,
                SUCCESS,
                FAILURE, ERROR).handleDownload();

    }

}
