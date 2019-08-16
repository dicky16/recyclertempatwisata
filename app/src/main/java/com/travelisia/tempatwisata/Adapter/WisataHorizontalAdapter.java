package com.travelisia.tempatwisata.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.travelisia.tempatwisata.Model.Wisata;
import com.travelisia.tempatwisata.R;

import java.util.ArrayList;

public class WisataHorizontalAdapter extends RecyclerView.Adapter<WisataHorizontalAdapter.ViewHolder> {
    private ArrayList<Wisata> listWisata;
    private OnItemWisataClick onItemWisataClick;
    public WisataHorizontalAdapter(ArrayList<Wisata> listWisata) {
        this.listWisata = listWisata;
    }
    public void setOnItemWisataClick(OnItemWisataClick onItemWisataClick){
        this.onItemWisataClick = onItemWisataClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wisata_horizontal,
                        parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Wisata wisata = listWisata.get(position);
    holder.namaWisata.setText(wisata.getNama_wisata());
        Glide.with(holder.itemView.getContext())
                .load(wisata.getImage_wisata())
//                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.ic_launcher_background)
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView namaWisata;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo_horizontal);
            namaWisata = itemView.findViewById(R.id.tv_item_nama_horizontal);
        }
    }
    public interface OnItemWisataClick{
        void onItemClicked(Wisata data);
    }
}
