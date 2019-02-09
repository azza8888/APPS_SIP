package com.ezatech.apps_sip.api;

public class UtilsApi {

//    public static final String BASE_URL_API ="http://sip.ezatech.com/api/";
      public  static final String BASE_URL_API="http://slo.sertifikasiinstalasiprima.co.id/api/";

    public static BaseApi getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApi.class);
    }
}
