package com.ezatech.apps_sip.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaseApi {

    @FormUrlEncoded
    @Headers({"Autorization : token"})
    @POST("login")
    Call<ResponseBody> login(
                             @Field("username")String username,
                             @Field("password")String password);


    @Headers({"Autorization : token"})
    @GET("logout")
    Call<ResponseBody> LogOut(@Query("token")String token);
}
