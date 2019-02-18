package com.ezatech.apps_sip.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL_API="https://slo.sertifikasiinstalasiprima.co.id/api/";
    private static Retrofit retrofit = null;
    private static String token= "acces_token";


    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

//        if (retrofit == null){
           Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            return retrofit;
        }
        public static BaseApi getAPIService() {
        return getClient().create(BaseApi.class);
        }


    private static Retrofit getClient2(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest = chain.request().newBuilder()
//                        .addHeader("Autorization", "Bearer" +token)
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static BaseApi getInstanceRetrofit(){
        return getClient2().create(BaseApi.class);
    }
}
