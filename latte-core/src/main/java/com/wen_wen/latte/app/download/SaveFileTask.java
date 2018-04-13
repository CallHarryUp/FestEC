package com.wen_wen.latte.app.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.wen_wen.latte.app.app.Latte;
import com.wen_wen.latte.app.net.callback.ISuccess;
import com.wen_wen.latte.app.net.callback.Irequest;
import com.wen_wen.latte.app.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by WeLot on 2018/4/13.
 * 存储文件任务类
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final Irequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(Irequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        InputStream is = body.byteStream();
        final String name = (String) params[3];
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {

        }
        //没有名字  先创建一个名字
        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toLowerCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);//如果存在名字 直接写入
        }

    }

    //主线程
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {//下载成功 获取文件路径
            SUCCESS.OnSuccess(file.getPath());
        }
        if (REQUEST != null) { //请求结束
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    //如果是下载的apk文件直接安装
    private void autoInstallApk(File file) {
        //取出文件的后缀名
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//新建一个栈  因为有可能时候后台进行的
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);

        }
    }

}
