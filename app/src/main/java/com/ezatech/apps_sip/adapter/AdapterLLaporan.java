package com.ezatech.apps_sip.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ezatech.apps_sip.R;
import com.ezatech.apps_sip.data.ListLaporan;
import com.ezatech.apps_sip.notifLaporan.DetailLapActivity;
import com.ezatech.apps_sip.notifLaporan.ListLaporanActivity;

import java.util.ArrayList;

public class AdapterLLaporan extends RecyclerView.Adapter<AdapterLLaporan.AdapterViewHolder> implements SwipeRefreshLayout.OnRefreshListener {


    private ArrayList<ListLaporan> list;
    private Context context;
    private int post1;
    private Button btnSelesai;

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
    public void onBindViewHolder(final AdapterViewHolder holder, int position) {
        ListLaporan laporan = list.get(position);
        holder.tvNosurat.setText(laporan.getNo_surat());
        holder.tvIdlist.setText(laporan.getId());
        holder.etNamap1.setText(laporan.getNama_pemeriksa1());
        holder.etNamap2.setText(laporan.getNama_pemeriksa2());
        holder.etStatus.setText(laporan.getStatus());
//        holder.swipeBtnSelesai.setColorSchemeResources(R.color.colorPrimaryDark1,R.color.colorPrimaryDark,R.color.colorPrimary);
//        holder.swipeBtnSelesai.setOnRefreshListener(AdapterLLaporan.this);
//        holder.swipeBtnSelesai.post(new Runnable() {
//                           @Override
//                           public void run() {
//                               holder.swipeBtnSelesai.setRefreshing(true);
//                           }
//                       }
//        );
//        holder.btnSelesai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((ListLaporanActivity) context).functionToSelesai();
//            }
//        });
        holder.btnCetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListLaporanActivity) context).functionToRun();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onRefresh() {

//        ((ListLaporanActivity) context).functionToSelesai();
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNosurat, tvIdlist;
        private EditText etNamap1, etNamap2, etStatus;
        private Button btnPeriksa, btnCetak, btnSelesai;
//        private SwipeRefreshLayout swipeBtnSelesai;
//        private ImageView ivList;

        public AdapterViewHolder(View itemView) {
            super(itemView);

//            ivList = (ImageView) itemView.findViewById(R.id.iv_list);
            tvIdlist = (TextView) itemView.findViewById(R.id.tv_idlist);
            tvIdlist.setVisibility(View.INVISIBLE);
            tvNosurat = (TextView) itemView.findViewById(R.id.tv_nosurat);
            etNamap1 = (EditText) itemView.findViewById(R.id.et_namap1);
            etNamap2 = (EditText) itemView.findViewById(R.id.et_namap2);
            etStatus = (EditText) itemView.findViewById(R.id.et_status);
            btnPeriksa = (Button) itemView.findViewById(R.id.btn_periksa);
//            btnSelesai = (Button) itemView.findViewById(R.id.btn_selesai);
//            swipeBtnSelesai = (SwipeRefreshLayout) itemView.findViewById(R.id.swipe_btnSelesai);
            btnPeriksa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    post1 = getAdapterPosition();
                    Intent intent = new Intent(context, DetailLapActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("id", tvIdlist.getText().toString().trim());
                    context.startActivity(intent);

                }
            });
            btnCetak = (Button) itemView.findViewById(R.id.btn_cetak);
        }
    }
}
