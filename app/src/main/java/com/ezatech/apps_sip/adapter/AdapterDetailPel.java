package com.ezatech.apps_sip.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.ezatech.apps_sip.uploadLaporan.FormActivity;

import java.util.ArrayList;

public class AdapterDetailPel extends RecyclerView.Adapter<AdapterDetailPel.AdapterHolder> {

    private ArrayList<Pelanggan> pelanggans;
    private Context context;
    private int post;


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
    public void onBindViewHolder(final AdapterHolder holder, final int position) {

        Pelanggan pelanggan = pelanggans.get(position);
        holder.tvIdpenerbit.setText(pelanggan.getId_penerbit());
        holder.tvNopendaf.setText(pelanggan.getNo_pendaftaran());
        holder.etNamapel.setText(pelanggan.getNama());
        holder.etAlamatpel.setText(pelanggan.getAlamat());
        holder.etTarifpel.setText(pelanggan.getJenis_tarif());
        holder.etDayapel.setText(pelanggan.getDaya());
        holder.etBtlpel.setText(pelanggan.getNama_penyedia());
//        holder.btnPeriksapel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                ((DetailLapActivity) context).goToPerksa();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return pelanggans.size();
    }


    public class AdapterHolder extends RecyclerView.ViewHolder {
        private TextView tvIdpenerbit;
        private Button tvNopendaf;
        private EditText etNamapel;
        private EditText etAlamatpel;
        private EditText etTarifpel;
        private EditText etDayapel;
        private EditText etBtlpel;
        private Button btnPeriksapel;
        public AdapterHolder(View itemView) {
            super(itemView);

            tvIdpenerbit = (TextView) itemView.findViewById(R.id.tv_idpenerbit);
            tvIdpenerbit.setVisibility(View.INVISIBLE);
            tvNopendaf = (Button) itemView.findViewById(R.id.tv_nopendaf);
            tvNopendaf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    post = getAdapterPosition();

                    Intent intent = new Intent(context, FormActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("no_pendaftaran", tvNopendaf.getText().toString().trim());
                    context.startActivity(intent);
                }
            });
            etNamapel = (EditText) itemView.findViewById(R.id.et_namapel);
            etAlamatpel = (EditText) itemView.findViewById(R.id.et_alamatpel);
            etTarifpel = (EditText) itemView.findViewById(R.id.et_tarifpel);
            etDayapel = (EditText) itemView.findViewById(R.id.et_dayapel);
            etBtlpel = (EditText) itemView.findViewById(R.id.et_btlpel);
        }
    }
}
