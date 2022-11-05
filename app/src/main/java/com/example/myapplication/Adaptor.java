package com.example.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter<Adaptor.ViewHolder> {

    private ArrayList<DataItem> DataItem;

    private RecyclerViewInterface View;
    public Adaptor(ArrayList<DataItem> dataItem ,RecyclerViewInterface view) {
        DataItem = dataItem;
        View = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem,View);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(DataItem.get(position).getTitle());
        holder.des.setText(DataItem.get(position).getDes());
        holder.price.setText(DataItem.get(position).getPrice());
        holder.fav.setImageResource(DataItem.get(position).getFav());

        Glide.with(holder.img).load(DataItem.get(position).getImg()).into(holder.img);


    }


    @Override
    public int getItemCount() {
        return DataItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public ImageView fav;
        public TextView title;
        public TextView des;
        public TextView price;


        public ViewHolder(@NonNull View itemView, RecyclerViewInterface view) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            fav = itemView.findViewById(R.id.fav);
            title = itemView.findViewById(R.id.title);
            des = itemView.findViewById(R.id.des);
            price = itemView.findViewById(R.id.price);

            itemView.setOnClickListener(v -> {
                if (view != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION ){
                        view.onClickListener(position);
                    }
                }
            });

        }

    }
}


