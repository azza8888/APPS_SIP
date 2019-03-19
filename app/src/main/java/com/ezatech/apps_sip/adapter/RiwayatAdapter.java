package com.ezatech.apps_sip.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.data.FeedbackData;
import com.ezatech.apps_sip.data.ListLaporan;
import com.ezatech.apps_sip.notifLaporan.DetailLapActivity;
import com.ezatech.apps_sip.riwayatNotif.DetailRwActivity;

import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {

    private ArrayList<ListLaporan> data;
    private Context context;
    private int post2;


    public RiwayatAdapter(Context context, ArrayList<ListLaporan> data) {
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

        ListLaporan laporan = data.get(position);
        holder.tvIdlistrw.setText(laporan.getId());
        holder.tvNosuratrw.setText(laporan.getNo_surat());
        holder.etNamap1rw.setText(laporan.getNama_pemeriksa1());
        holder.etNamap2rw.setText(laporan.getNama_pemeriksa2());
        holder.etStatusrw.setText(laporan.getStatus());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    public class RiwayatViewHolder extends RecyclerView.ViewHolder {
        private ImageView img6;
        private TextView tvIdlistrw;
        private TextView tvNosuratrw;
        private EditText etNamap1rw;
        private EditText etNamap2rw;
        private EditText etStatusrw;


        public RiwayatViewHolder(View itemView) {
            super(itemView);

            tvIdlistrw = (TextView) itemView.findViewById(R.id.tv_idlistrw);
            tvNosuratrw = (TextView) itemView.findViewById(R.id.tv_nosuratrw);
            tvNosuratrw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    post2 = getAdapterPosition();
                    Intent intent = new Intent(context, DetailRwActivity.class);
                    intent.putExtra("id", tvIdlistrw.getText().toString().trim());
                    context.startActivity(intent);
                }
            });
            etNamap1rw = (EditText) itemView.findViewById(R.id.et_namap1rw);
            etNamap2rw = (EditText) itemView.findViewById(R.id.et_namap2rw);
            etStatusrw = (EditText) itemView.findViewById(R.id.et_statusrw);

        }
    }
}
