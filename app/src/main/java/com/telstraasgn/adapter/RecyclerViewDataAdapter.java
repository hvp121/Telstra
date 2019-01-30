package com.telstraasgn.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.telstraasgn.R;
import com.telstraasgn.model.CountryData;

import java.util.ArrayList;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.CountryViewHolder> {
    private ArrayList<CountryData> dataList;
    public RecyclerViewDataAdapter(ArrayList<CountryData> dataList ) {
        this.dataList = dataList;
    }
    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_row, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtDataTitle.setText(dataList.get(position).getTitle());
        holder.txtDataBrief.setText(dataList.get(position).getDescription());
        //holder.txtNoticeFilePath.setText(dataList.get(position).getFileSource());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    class CountryViewHolder extends RecyclerView.ViewHolder {

        TextView txtDataTitle, txtDataBrief, txtNoticeFilePath;

        CountryViewHolder(View itemView) {
            super(itemView);
            txtDataTitle =  itemView.findViewById(R.id.txt_data_title);
            txtDataBrief =  itemView.findViewById(R.id.txt_data_brief);
            //txtNoticeFilePath =  itemView.findViewById(R.id.txt_notice_file_path);

        }
    }
}
