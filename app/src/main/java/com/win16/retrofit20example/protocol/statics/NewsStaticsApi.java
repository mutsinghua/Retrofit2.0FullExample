package com.win16.retrofit20example.protocol.statics;


import com.win16.retrofit20example.TypeString;
import com.win16.retrofit20example.protocol.statics.pojo.NewsStatResPojo;

import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author REXZOU
 * @Description:
 * @date 2016/2/23 16:00
 * @copyright TCL-MIG
 */
public interface NewsStaticsApi {

    @Multipart
    @POST("newsStats")
//    Call<NewsStatResPojo> reportStat(@Part("news_stat") RequestBody news_stat, @Part("version_name") RequestBody version_name,
//                                     @Part("model") RequestBody model, @Part("CU") RequestBody cu,
//                                     @Part("os_version_code") RequestBody os_version_code, @Part("imei") RequestBody imei,
//                                     @Part("screen_size") RequestBody screen_size, @Part("language") RequestBody language,
//                                     @Part("imsi") RequestBody imsi,@Part("expect_server_compress") RequestBody expect_server_compress,
//                                     @Part("network") String network, @Part("version_code") RequestBody version_code );

    Call<NewsStatResPojo> reportStat(@Part("news_stat") @TypeString String news_stat);
}
