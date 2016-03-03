package com.win16.retrofit20example.protocol.appcenter;


import com.win16.retrofit20example.TypeString;
import com.win16.retrofit20example.protocol.ResponseResult;
import com.win16.retrofit20example.protocol.appcenter.pojo.MiniAppCenterPojo;
import com.win16.retrofit20example.protocol.appcenter.pojo.Screen1Pojo;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author REXZOU
 * @Description:
 * @date 2016/2/23 13:35
 * @copyright TCL-MIG
 */
public interface AppListApi {


    //GET示例
    @GET("applist/screen1/")
    Call<Screen1Pojo> getAppList(@Query("offset") int offset, @Query("limit") int limit);

    //自定义注解与转换示例
    @GET("applist/mini-appcenter/")
    Call<MiniAppCenterPojo> getMiniApp(@Query("offsets") @TypeString String offsets);

    //完整URL示例
    @GET("http://www.baidu.com/apk/test")
    Call<ResponseResult> testApi(@Query("offset") int offset);

    //动态路径示例
    @GET("applist/apps/{appid}/detail")
    Call<ResponseResult> getDetail(@Path("appid") String appid);

    //动态参数示例
    @GET("applist/apps/detail")
    Call<ResponseResult> getDetail(@QueryMap Map<String, String> param);

    //动态参数与静态参数混搭示例
    @GET("applist/apps/detail?type=detail")
    Call<ResponseResult> getAppInfo(@Query("appid") String appid);

    //静态Header
    @Headers("Cache:no-Cache")
    @GET("applist/apps/detail?type=detail")
    Call<ResponseResult> getDetail2 (@Query("appid") String appid);

    //动态Header
    @GET("applist/apps/detail?type=detail")
    Call<ResponseResult> getDetail3(@Header("Accept-Encoding") String appid);

    //多个Header
    @Headers({ "X-Foo: Bar", "X-Ping: Pong" })
    @GET("applist/apps/detail?type=detail")
    Call<ResponseResult> getDetail4(@Header ("Accept-Encoding") String appid);

    //固定与动态Header的混搭
    @Headers("Accept-Encoding:application/json")
    @GET("applist/apps/detail?type=detail")
    Call<ResponseResult> getDetail5(@Header ("Location") String appid);

    //POST基本示例
    @POST("applist/apps/detail")
    Call<ResponseResult> sendDetail(@Body RequestBody requestData);

    //提交表单数据
    @FormUrlEncoded
    @POST("applist/apps/detail")
    Call<ResponseResult> sendDetail2(@Field("name1") String name1, @Field("name2") String name2);

    //动态表单
    @FormUrlEncoded @POST("applist/apps/detail")
    Call<ResponseResult> sendDetail2(@FieldMap Map<String, String> names);

    //Multipart的写法
    @Multipart
    @POST("applist/apps/detail")
    Call<ResponseResult> sendDetail3(@Part("name1") String name1,@Part("name2") String name2);

    @Multipart
    @POST("applist/apps/detail")
    Call<ResponseResult> sendDetail4(@PartMap Map<String, String> names);

    //上传文件
    @POST("applist/apps/detail")
    Call<ResponseResult> sendFile(RequestBody fileRequest);

    //上传多个文件示例
    @Multipart
    @POST("applist/apps/detail")
    Call<ResponseResult> sendFile(@Part("filename=test1") RequestBody fileRequest1 ,@Part("filename=test2") RequestBody fileRequest2);
}
