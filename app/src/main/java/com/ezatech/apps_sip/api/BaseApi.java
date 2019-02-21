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
import retrofit2.http.Path;
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

    @GET("pemeriksaan/add/{no_pendaftaran}")
    Call<ResponseBody> pemeriksaGetNopend(@Header("Authorization")String acces_token,
                                      @Path("no_pendaftaran")String no_pend);

    @GET("suratku/detail/{id}")
    Call<ResponseBody> detailPendaftar(@Header("Authorization")String acces_token,
                                       @Path("id")String id);

    @FormUrlEncoded
    @POST("pemeriksa")
    Call<ResponseBody> unggahPemeriksa(
            @Header("Authorization")String acces_token,
            @Field("no_lhpp")String no_lhpp,
            @Field("tgl_lhpp")String tgl_lhpp,
            @Field("gambar_instalasi")String gambar_instalasi,
            @Field("diagram_garis_tunggal")String digramgt,
            @Field("pe_utama")String pe_utama,
            @Field("pe_cabang")String pe_cabang,
            @Field("pe_akhir")String pe_akhir,
            @Field("pe_kotak_kontak")String pe_kotakk,
            @Field("jenis_peng_utama")String jenispu,
            @Field("jenis_peng_cabang")String jenispc,
            @Field("penghantar_bumi_jenis")String penghantarbj,
            @Field("penghantar_bumi_penampang")String penghantarbp,
            @Field("penghantar_bumi_sistem")String penghantarbs,
            @Field("saklar_utama")String saklar_utama,
            @Field("saklar_cabang1")String saklar_cb1,
            @Field("saklar_cabang2")String saklar_cb2,
            @Field("phbk_cabang1")String phbk_cb1,
            @Field("phbk_cabang2")String phbk_cb2,
            @Field("penghantar_utama")String penghantar_utama,
            @Field("penghantar_cabang")String penghantar_cb,
            @Field("penghantar_akhir")String penghantar_ar,
            @Field("penghantar_3fasa")String penghantar_3f,
            @Field("fitting_lampu")String fitting_lamp,
            @Field("kotak_kontak")String kotak_kontak,
            @Field("sakelar")String sakelar,
            @Field("tinggi_kotak_kontak")String tinggikk,
            @Field("tinggi_phbk")String tinggi_phbk,
            @Field("jenis_kotak_kontak")String jeniskk,
            @Field("tanda_komponen")String tanda_komponen,
            @Field("pengujian_pembebanan")String pengujian_pembebanan,
            @Field("jml_phb_utama")String jml_pu,
            @Field("jml_phb_1fasa")String jml_p1f,
            @Field("jml_phb_3fasa")String jml_p3f,
            @Field("jml_phb_cabang")String jml_pcb,
            @Field("jml_saluran_cabang")String jml_cb,
            @Field("jml_saluran_akhir")String jml_sa,
            @Field("jml_titik_lampu")String jml_tl,
            @Field("jml_sakelar")String jml_sakelar,
            @Field("kkb")String kkb,
            @Field("kkk")String kkk,
            @Field("tahanan_isolasi_penghantar")String tahanan_ip,
            @Field("resisten_pembumian")String resisten_pmb,
            @Field("jml_motor_listrik_unit")String jml_mlu,
            @Field("jml_motor_listrik_kwh")String jml_mlk,
            @Field("catatan")String catatan,
            @Field("location")String location,
            @Field("lat")String lat,
            @Field("lng")String lng
    );

    @GET("load-daya")
    Call<ResponseBody> load_daya(@Header("Authorization")String token);
}
