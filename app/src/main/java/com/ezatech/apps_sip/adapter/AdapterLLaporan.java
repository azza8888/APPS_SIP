package com.ezatech.apps_sip.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.data.ListLaporan;
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
        holder.tvNamalist.setText(laporan.getNama());
        holder.tvAlamatlist.setText(laporan.getAlamat());
        holder.tvWaktulist.setText(laporan.getWaktu());
        Picasso.with(context).load(R.drawable.ic_info_black_24dp)
                .into(holder.ivList);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamalist, tvAlamatlist, tvWaktulist;
        private ImageView ivList;

        public AdapterViewHolder(View itemView) {
            super(itemView);

            ivList = (ImageView) itemView.findViewById(R.id.iv_list);
            tvNamalist = (TextView) itemView.findViewById(R.id.tv_namalist);
            tvAlamatlist = (TextView) itemView.findViewById(R.id.tv_alamatlist);
            tvWaktulist = (TextView) itemView.findViewById(R.id.tv_waktulist);
        }
    }
}
