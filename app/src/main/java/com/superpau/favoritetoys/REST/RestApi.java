package com.superpau.favoritetoys.REST;

import com.superpau.favoritetoys.Model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Buat mengatur callback dari retrofit
 */

public interface RestApi {

    @GET("movie/now_playing")
    Call<Result> getNowPlaying(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<Result> getUpComing(@Query("api_key") String api_key);

    @GET("movie/popular")
    Call<Result> getPopular(@Query("api_key") String api_key);
}
