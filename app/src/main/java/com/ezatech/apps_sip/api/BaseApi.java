package com.ezatech.apps_sip.api;

import com.ezatech.apps_sip.data.FormResultModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @Multipart
    @POST("pemeriksa")
    Call<FormResultModel> unggahPemeriksa(
            @Header("Authorization") String acces_token,
            @Part("no_pendaftaran")RequestBody no_pendaftaran,
            @Part("no_surat_tugas")RequestBody no_surat_tgs,
            @Part("no_pemeriksaan")RequestBody no_pemeriksaan,
            @Part("no_lhpp")RequestBody no_lhpp,
            @Part("tgl_lhpp")RequestBody tgl_lhpp,
            @Part("gambar_instalasi")RequestBody gambar_instalasi,
            @Part("diagram_garis_tunggal")RequestBody digramgt,
            @Part("pe_utama")RequestBody pe_utama,
            @Part("pe_cabang")RequestBody pe_cabang,
            @Part("pe_akhir")RequestBody pe_akhir,
            @Part("pe_kotak_kontak")RequestBody pe_kotakk,
            @Part("jenis_peng_utama")RequestBody jenispu,
            @Part("jenis_peng_cabang")RequestBody jenispc,
            @Part("jenis_peng_akhir")RequestBody jenispakhir,
            @Part("penghantar_bumi_jenis")RequestBody penghantarbj,
            @Part("penghantar_bumi_penampang")RequestBody penghantarbp,
            @Part("penghantar_bumi_sistem")RequestBody penghantarbs,
            @Part("saklar_utama")RequestBody saklar_utama,
            @Part("saklar_cabang1")RequestBody saklar_cb1,
            @Part("saklar_cabang2")RequestBody saklar_cb2,
            @Part("phbk_utama")RequestBody phbk_utama,
            @Part("phbk_cabang1")RequestBody phbk_cb1,
            @Part("phbk_cabang2")RequestBody phbk_cb2,
            @Part("penghantar_utama")RequestBody penghantar_utama,
            @Part("penghantar_cabang")RequestBody penghantar_cb,
            @Part("penghantar_akhir")RequestBody penghantar_ar,
            @Part("penghantar_3fasa")RequestBody penghantar_3f,
            @Part("fitting_lampu")RequestBody fitting_lamp,
            @Part("kotak_kontak")RequestBody kotak_kontak,
            @Part("sakelar")RequestBody sakelar,
            @Part("tinggi_kotak_kontak")RequestBody tinggikk,
            @Part("tinggi_phbk")RequestBody tinggi_phbk,
            @Part("jenis_kotak_kontak")RequestBody jeniskk,
            @Part("tanda_komponen")RequestBody tanda_komponen,
            @Part("pengujian_pembebanan")RequestBody pengujian_pembebanan,
            @Part("jml_phb_utama")RequestBody jml_pu,
            @Part("jml_phb_1fasa")RequestBody jml_p1f,
            @Part("jml_phb_3fasa")RequestBody jml_p3f,
            @Part("jml_phb_cabang")RequestBody jml_pcb,
            @Part("jml_saluran_cabang")RequestBody jml_cb,
            @Part("jml_saluran_akhir")RequestBody jml_sa,
            @Part("jml_titik_lampu")RequestBody jml_tl,
            @Part("jml_sakelar")RequestBody jml_sakelar,
            @Part("kkb")RequestBody kkb,
            @Part("kkk")RequestBody kkk,
            @Part("tahanan_isolasi_penghantar")RequestBody tahanan_ip,
            @Part("resisten_pembumian")RequestBody resisten_pmb,
            @Part("jml_motor_listrik_unit")RequestBody jml_mlu,
            @Part("jml_motor_listrik_kwh")RequestBody jml_mlk,
            @Part("catatan")RequestBody catatan,
            @Part("location")RequestBody location,
            @Part("lat")RequestBody lat,
            @Part("lng")RequestBody lng,
            @Part MultipartBody.Part file,
            @Part MultipartBody.Part file2,
            @Part MultipartBody.Part file3,
            @Part MultipartBody.Part file4,
            @Part MultipartBody.Part file5
    );

    @GET("generate-lhpp")
    Call<ResponseBody> getNoLhpp(@Header("Authorization")String acces_token);

    @FormUrlEncoded
    @POST("pemeriksa")
    Call<ResponseBody> uploadPemeriksa(
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
            @Field("lng")String lng,
            @Field("foto1") String foto1);


    @GET("load-daya")
    Call<ResponseBody> load_daya(@Header("Authorization")String token);
}
