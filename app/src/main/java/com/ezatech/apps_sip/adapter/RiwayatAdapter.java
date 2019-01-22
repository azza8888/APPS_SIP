package com.ezatech.apps_sip.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.data.FeedbackData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {

    private ArrayList<FeedbackData> data;
    private Context context;

    public RiwayatAdapter(Context context, ArrayList<FeedbackData> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RiwayatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_row_riwayat, parent, false);
        return new RiwayatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RiwayatViewHolder holder, int position) {

        FeedbackData data1 = data.get(position);
        holder.tvNama.setText(data1.getNama());
        holder.tvUpdateWaktu.setText(data1.getWaktu());
        holder.tvConfrim.setText(data1.getKonfirm());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class RiwayatViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvWaktu, tvConfrim, tvUpdateWaktu;
        private ImageView img6;


        public RiwayatViewHolder(View itemView) {
            super(itemView);

            tvNama = (TextView) itemView.findViewById(R.id.view_nama);
            img6 = (ImageView) itemView.findViewById(R.id.img6);
            tvUpdateWaktu = (TextView) itemView.findViewById(R.id.view_update_waktu_feedback);
            tvWaktu = (TextView) itemView.findViewById(R.id.view_waktu);
            tvConfrim = (TextView) itemView.findViewById(R.id.view_konfirm);
        }
    }
}
