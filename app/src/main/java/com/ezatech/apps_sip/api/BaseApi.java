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
    @POST("login")
    Call<ResponseBody> login(
            @Field("username")String username,
            @Field("password")String password);


//    @Headers({"Authorization : Bearer token"})
    @GET("logout")
    Call<ResponseBody> logOut(@Header("Authorization") String token);

    //    @Headers({"Accept : application/json"})
    @GET("profile/app")
    Call<ResponseBody> profile(
            @Header("Authorization")String acces_token
    );

    @GET("suratku")
    Call<ResponseBody> suratPerintah(@Header("Authorization")String token);

    @Multipart
    @POST("profile/edit")
    Call<ResponseBody> token_edt(@Header("Authorization")String acces_token);

    @FormUrlEncoded
//    @Headers({"Authorization : Bearer token"})
    @POST("profile/edit")
    Call<ResponseBody> editProfile(
            @Header("Authorization") String auth,
            @Field("nama")String nama,
            @Field("username")String username,
            @Field("nip")String nip);

    @FormUrlEncoded
//    @Headers({"Authorization : Bearer token"})
    @POST("edit-password")
    Call<ResponseBody> ubahPassword(@Header("Authorization")String token,
                                    @Field("current_password")String current_password,
                                    @Field("new_password")String new_password,
                                    @Field("new_password_confirmation")String new_password_confirmation);



    @GET("load-daya")
    Call<ResponseBody> load_daya(@Header("Authorization")String token);
}
