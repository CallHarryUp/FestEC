package com.wen_wen.latte.app.net.rx;

/**
 * Created by WeLot on 2018/4/12.
 */

import android.content.Context;

import com.wen_wen.latte.app.net.HttpMethod;
import com.wen_wen.latte.app.net.RestCreator;
import com.wen_wen.latte.app.ui.loader.LatteLoader;
import com.wen_wen.latte.app.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 设计模式：
 * 1、使用建造者模式
 * 2、 请求时加入loader
 * 3、完善请求方式
 * 4、完善download下载文件方式
 */
public class RxRestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private File FILE;


    public RxRestClient(String url, Map<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle

    ) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    //创建建造者
    public static RxRestClientBuilder builder() {

        return new RxRestClientBuilder();
    }

    //创建请求方式
    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;


        //在请求开始是 调用loader  展示
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAM:
                observable = service.putRaw(URL, BODY);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD://使用retrofit的方法
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.
                        createFormData("file", FILE.getName(), requestBody);
                observable = RestCreator.getRxRestService().upload(URL, body);
                break;
            default:
                break;
        }

        return observable;
    }

    //获取实例
   /* private Callback<String> getRequestCallback() {

        return new RequestCallbacks(REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE);
    }*/

    //创建请求方法
    public final Observable<String> get() {

        return request(HttpMethod.GET);
    }

    //对post进行判断  是否请求原始数据
    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params  must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }

    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAM);
        }


    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }


    public Observable<ResponseBody> download() {

        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL, PARAMS);

        return responseBodyObservable;

       /* return new DownloadHanlder(URL,
                REQUEST,
                DOWNLOAD_DIR,
                EXTENSION,
                NAME,
                SUCCESS,
                FAILURE, ERROR).handleDownload();*/

    }

}
