package com.win16.retrofit20example.protocol.wallpaper;

import com.win16.retrofit20example.protocol.wallpaper.pojo.WallPaperPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author REXZOU
 * @Description:
 * @date 2016/2/23 15:42
 * @copyright TCL-MIG
 */
public interface WallPaperApi {

    @GET("wallpapers/")
    Call<WallPaperPojo> getWallPaper(@Url String url, @Query("offset") int offset, @Query("limit") int limit);
}
