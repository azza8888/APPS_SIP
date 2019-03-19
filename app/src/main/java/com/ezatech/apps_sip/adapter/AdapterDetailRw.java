package com.ezatech.apps_sip.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.data.Pelanggan;

import java.util.ArrayList;

public class AdapterDetailRw extends RecyclerView.Adapter<AdapterDetailRw.AdapterHolder> {
    private ArrayList<Pelanggan> pelanggans;
    private Context context;


    public AdapterDetailRw(Context context, ArrayList<Pelanggan> pelanggans) {
        this.pelanggans = pelanggans;
        this.context = context;
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_detail_pelanggan, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        Pelanggan pelanggan = pelanggans.get(position);
        holder.tvIdpenerbit.setText(pelanggan.getId_penerbit());
        holder.tvNopendaf.setText(pelanggan.getNo_pendaftaran());
        holder.etNamapel.setText(pelanggan.getNama());
        holder.etAlamatpel.setText(pelanggan.getAlamat());
        holder.etTarifpel.setText(pelanggan.getJenis_tarif());
        holder.etDayapel.setText(pelanggan.getDaya());
        holder.etBtlpel.setText(pelanggan.getNama_penyedia());

    }

    @Override
    public int getItemCount() {
        return pelanggans.size();
    }

    private void initView() {

    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        private TextView tvIdpenerbit;
        private Button tvNopendaf;
        private EditText etNamapel;
        private EditText etAlamatpel;
        private EditText etTarifpel;
        private EditText etDayapel;
        private EditText etBtlpel;
        public AdapterHolder(View itemView) {
            super(itemView);
            tvIdpenerbit = (TextView) itemView.findViewById(R.id.tv_idpenerbit);
            tvNopendaf = (Button) itemView.findViewById(R.id.tv_nopendaf);
            etNamapel = (EditText) itemView.findViewById(R.id.et_namapel);
            etAlamatpel = (EditText) itemView.findViewById(R.id.et_alamatpel);
            etTarifpel = (EditText) itemView.findViewById(R.id.et_tarifpel);
            etDayapel = (EditText) itemView.findViewById(R.id.et_dayapel);
            etBtlpel = (EditText) itemView.findViewById(R.id.et_btlpel);
        }
    }
}
