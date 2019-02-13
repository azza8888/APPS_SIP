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
    @Headers({"Autorization : Bearer token"})
    @POST("login")
    Call<ResponseBody> login(
                             @Field("username")String username,
                             @Field("password")String password);


    @Headers({"Autorization : Bearer token"})
    @GET("logout")
    Call<ResponseBody> LogOut(@Query("acces_token")String token);

    @Headers({"Autorization : Bearer token",
    "Accept : application/json"})
    @GET("profile/app")
    Call<ResponseBody> profile(@Header("Autorization")String acces_token);

    @Multipart
    @POST("profile/edit")
    Call<ResponseBody> token_edt(@Header("Autorization")String acces_token);

    @FormUrlEncoded
//    @Headers({"Autorization : Bearer token"})
    @POST("profile/edit")
    Call<ResponseBody> EditProfile(@Header("Autorization")String token,
                                   @Field("nama")String nama,
                                   @Field("nip")String nip,
                                   @Field("username")String username);

    @FormUrlEncoded
    @Headers({"Autorization : Bearer token"})
    @POST("edit-password")
    Call<ResponseBody> ubahPassword(@Field("current_password")String current_password,
                                    @Field("new_password")String new_password,
                                    @Field("new_password_confirmation")String new_password_confirmation);
}
