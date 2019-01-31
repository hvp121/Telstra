package com.telstraasgn.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.telstraasgn.R;
import com.telstraasgn.model.CountryData;

import java.util.ArrayList;

//RecyclerView Adapter Class
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

        /*Picasso.with(context)
                .load("http://i.imgur.com/DvpvklR.png") //URL/FILE
                .into(imageView);*/


        Picasso.get().load(dataList.get(position).getImageHref()).placeholder(R.drawable.image_not_available)
                .into(holder.imgData);

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

        //@BindView(R.id.txt_data_title) TextView txtDataTitle;
        //@BindView(R.id.txt_data_brief) TextView txtDataBrief;
        //@BindView(R.id.img_data) ImageView imgData;
        TextView txtDataTitle, txtDataBrief;
        ImageView imgData;

        CountryViewHolder(View itemView) {
            super(itemView);
            txtDataTitle =  itemView.findViewById(R.id.txt_data_title);
            txtDataBrief =  itemView.findViewById(R.id.txt_data_brief);
            imgData =  itemView.findViewById(R.id.img_data);
            //ButterKnife.bind(this,itemView);
        }
    }
}
