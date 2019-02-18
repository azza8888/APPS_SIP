package com.ezatech.apps_sip.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.data.ListLaporan;
import com.ezatech.apps_sip.notifLaporan.DetailLapActivity;
import com.ezatech.apps_sip.notifLaporan.ListLaporanActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterLLaporan extends RecyclerView.Adapter<AdapterLLaporan.AdapterViewHolder> {


    private ArrayList<ListLaporan> list;
    private Context context;

    public AdapterLLaporan(Context context, ArrayList<ListLaporan> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_laporan, parent, false);
        return new AdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        ListLaporan laporan = list.get(position);
        holder.tvNosurat.setText(laporan.getNo_surat());
        holder.tvIdlist.setText(laporan.getId());
        holder.etNamap1.setText(laporan.getNama_pemeriksa1());
        holder.etNamap2.setText(laporan.getNama_pemeriksa2());
        holder.tvNosurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListLaporanActivity) context).functionToRun();
            }
        });
//        Picasso.with(context).load(R.drawable.ic_info_black_24dp)
//                .into(holder.ivList);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNosurat,tvIdlist;
        private EditText etNamap1, etNamap2;
//        private ImageView ivList;

        public AdapterViewHolder(View itemView) {
            super(itemView);

//            ivList = (ImageView) itemView.findViewById(R.id.iv_list);
            tvIdlist = (TextView) itemView.findViewById(R.id.tv_idlist);
            tvNosurat = (TextView) itemView.findViewById(R.id.tv_nosurat);
            etNamap1 = (EditText) itemView.findViewById(R.id.et_namap1);
            etNamap2 = (EditText) itemView.findViewById(R.id.et_namap2);
        }
    }
}
