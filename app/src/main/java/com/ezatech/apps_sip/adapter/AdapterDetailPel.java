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
import com.ezatech.apps_sip.notifLaporan.DetailLapActivity;

import java.util.ArrayList;

public class AdapterDetailPel extends RecyclerView.Adapter<AdapterDetailPel.AdapterHolder> {

    private ArrayList<Pelanggan> pelanggans;
    private Context context;


    public AdapterDetailPel(Context context, ArrayList<Pelanggan> pelanggans) {
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
        holder.btnPeriksapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((DetailLapActivity) context).goToPerksa();
            }
        });

    }

    @Override
    public int getItemCount() {
        return pelanggans.size();
    }


    public class AdapterHolder extends RecyclerView.ViewHolder {
        private TextView tvIdpenerbit;
        private TextView tvNopendaf;
        private EditText etNamapel;
        private EditText etAlamatpel;
        private EditText etTarifpel;
        private EditText etDayapel;
        private EditText etBtlpel;
        private Button btnPeriksapel;
        public AdapterHolder(View itemView) {
            super(itemView);

            tvIdpenerbit = (TextView) itemView.findViewById(R.id.tv_idpenerbit);
            tvNopendaf = (TextView) itemView.findViewById(R.id.tv_nopendaf);
            etNamapel = (EditText) itemView.findViewById(R.id.et_namapel);
            etAlamatpel = (EditText) itemView.findViewById(R.id.et_alamatpel);
            etTarifpel = (EditText) itemView.findViewById(R.id.et_tarifpel);
            etDayapel = (EditText) itemView.findViewById(R.id.et_dayapel);
            etBtlpel = (EditText) itemView.findViewById(R.id.et_btlpel);
            btnPeriksapel = (Button) itemView.findViewById(R.id.btn_periksapel);
        }
    }
}
