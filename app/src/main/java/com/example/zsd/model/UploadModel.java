package com.example.zsd.model;

import com.example.zsd.entity.Upload;
import com.example.zsd.utils.HttpUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static java.lang.String.valueOf;

/**
 * 作者： 张少丹
 * 时间：  2017/11/30.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class UploadModel {
    public void getUpload(String uid,String path){
        Observable<Upload> upload = new HttpUtils.Builder()
                .addCallAdapterFactory()
                .addConverterFactory()
                .build()
                .getMyQusetUtils()
                .getUpload();
        /*MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("uid",uid);
        builder.addFormDataPart("file",path);
        if(path != null){
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
            builder.addFormDataPart("jokeFiles", file.getName(), requestBody);
        }
        List<MultipartBody.Part> parts = builder.build().parts();*/
        upload.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Upload>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Upload value) {
                        if(value.code.equals("0")){
                            getUpload.getUploadSuccess(value);
                        }else {
                            getUpload.getUploadFailure(value.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private GetUpload getUpload;

    public void setGetUpload(GetUpload getUpload) {
        this.getUpload = getUpload;
    }

    public interface GetUpload{
        void getUploadSuccess(Upload value);
        void getUploadFailure(String msg);
    }
}
