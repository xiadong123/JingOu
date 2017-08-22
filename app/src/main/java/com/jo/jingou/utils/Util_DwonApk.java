package com.jo.jingou.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tencent.mm.opensdk.utils.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/8/11.
 */

public class Util_DwonApk {

    private File file;
    private Context context;
    public static final String TAG=Util_DwonApk.class.getSimpleName();

    public Util_DwonApk(Context context){
        this.context=context;
    }


    /**
     * 获取应用版本号
     */
    public String getVersion(){

        PackageManager packageManager = context.getPackageManager();

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载最新应用
     */
    public void doDownLoad(String apkUrl){
        try {
            //创建存储文件
            file = null;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                file =new File(Environment.getExternalStorageDirectory(),"jingOu.apk");
            }else {
                file =new File(Environment.getDataDirectory(),"jingOu.apk");
            }
            if (file.exists()){
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpClient okHttpClient = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(apkUrl)
                .build();

        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (response.isSuccessful()){
                            InputStream inputStream = response.body().byteStream();

                            byte[] buffer=new byte[1024];
                            FileOutputStream fos = new FileOutputStream(file);
                            int count = 0;
                            while ((count=inputStream.read(buffer))!=-1){
                                fos.write(buffer,0,count);
                                Log.i(TAG, "onResponse: "+count+" kb");
                            }
                            fos.flush();
                            fos.close();
                            inputStream.close();

                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            instanllApk();
                        }
                    }
                });
    }

    /**
     * 安装应用
     */
    private void instanllApk(){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断版本号
        if (Build.VERSION.SDK_INT>=24){
            Uri apkUrl = FileProvider.getUriForFile(context, "com.jo.jingou.provider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUrl,"application/vnd.android.package-archive");
        }else {
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
}
