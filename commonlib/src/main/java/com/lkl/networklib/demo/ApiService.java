package com.lkl.networklib.demo;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService{

    //获取豆瓣Top250榜单
    //top250 是尾址（完整的地址是：baseUrl+尾址）
    //Get请求：参数使用@Query标签，如果参数多的话可以用@QueryMap标签，接收一个Map。
    @GET("top250")
    Call<Object> getTop250Get(@Query("start") int start, @Query("count") int count);

    //Post请求：参数标签用 @Field 或者 @Body 或者 @FieldMap
    //Post请求：有参数时必须加上@FormUrlEncoded标签,否则会抛异常（没有参数时不需要@FormUrlEncoded标签-待验证）
    @FormUrlEncoded
    @POST("top250")
    Call<Object> getTop250Post(@Field("start") int start, @Field("count") int count);




    //更改定义的接口，返回值不再是一个Call,而是返回的一个Observble
    @GET("top250")
    Observable<Object> getTop250Observable(@Query("start") int start, @Query("count") int count);


    @FormUrlEncoded
    @POST("/x3/weatherlist")
    Observable<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);
}
