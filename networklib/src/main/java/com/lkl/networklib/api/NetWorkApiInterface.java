package com.lkl.networklib.api;

import com.lkl.networklib.entity.UploadFileEntity;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Likailing date:2018/9/7 0007 time:15:49
 * Email : 13297970902@163.com
 * Description :
 */

public interface NetWorkApiInterface {

    @Streaming
    @GET
    Flowable<ResponseBody> download(@Url String url);

    @POST
    Flowable<Response<UploadFileEntity>> upLoad(@Url String url, @Body MultipartBody body);
}
