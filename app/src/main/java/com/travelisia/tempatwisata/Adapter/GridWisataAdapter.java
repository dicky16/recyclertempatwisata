package com.travelisia.tempatwisata.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.travelisia.tempatwisata.Model.Wisata;
import com.travelisia.tempatwisata.R;

import java.util.ArrayList;

public class GridWisataAdapter extends RecyclerView.Adapter<GridWisataAdapter.GridViewHolder> {
    private ArrayList<Wisata> listWisata;
    private OnItemGridWisataClick onItemWisataClick;

    public GridWisataAdapter(ArrayList<Wisata> list){
        this.listWisata = list;
    }

    public void setOnItemWisataClick(OnItemGridWisataClick onItemWisataClick) {
        this.onItemWisataClick = onItemWisataClick;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wisata_grid,
                        parent,false);
        GridWisataAdapter.GridViewHolder vh = new GridViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position) {
        Wisata wisata = listWisata.get(position);
        Glide.with(holder.itemView.getContext())
                .load(wisata.getImage_wisata())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemWisataClick.onItemClicked(listWisata.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWisata.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo_grid);
        }
    }
    public interface OnItemGridWisataClick{
        void onItemClicked(Wisata data);
    }
}
