package com.travelisia.tempatwisata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.travelisia.tempatwisata.Adapter.WisataHorizontalAdapter;
import com.travelisia.tempatwisata.Data.WisataData;
import com.travelisia.tempatwisata.Model.Wisata;

import java.util.ArrayList;

import static com.travelisia.tempatwisata.MainActivity.Key_Wisata;

public class LihatDetail extends AppCompatActivity {
TextView tvNamaWisata,tvKeterangan,tvLokasi;
ImageView imgPhotoWisata;
private RecyclerView rvWisata;
    private ArrayList<Wisata> listWisata = new ArrayList<>();
//variable gmaps
String maps = "com.google.android.apps.maps";
    Uri mapsIntenUri;
    Intent mapsIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail);
        initComponents();
        lihatData();
        showRecyclerHorizontal();
        backToPrevious();
    }
    private void showRecyclerHorizontal(){
        rvWisata.setHasFixedSize(true);
        listWisata.addAll(WisataData.getListData());
        rvWisata.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        WisataHorizontalAdapter wisataHorizontalAdapter = new WisataHorizontalAdapter(listWisata);
        rvWisata.setAdapter(wisataHorizontalAdapter);
        wisataHorizontalAdapter.setOnItemWisataClick(new WisataHorizontalAdapter.OnItemWisataClick() {
            @Override
            public void onItemClicked(Wisata data) {
                getDataWisata(data);
            }
        });
    }
    private void getDataWisata(Wisata wisata){
        String namaWisata = wisata.getNama_wisata();
        String keterangan = wisata.getKeterangan();
        String lokasi = wisata.getLokasi();
        String imgUrl = wisata.getImage_wisata();
//        Wisata mWisata = new Wisata(namaWisata,keterangan,lokasi,imgUrl);
        Intent i = new Intent(this,LihatDetail.class);
        i.putExtra(Key_Wisata,new String[]{namaWisata,keterangan,lokasi,imgUrl});
        startActivity(i);

    }
    private void initComponents(){
    tvNamaWisata = findViewById(R.id.tv_nama_detail);
    tvKeterangan = findViewById(R.id.tv_keterangan_detail);
    tvLokasi = findViewById(R.id.tv_tempat_detail);
    imgPhotoWisata = findViewById(R.id.img_photo_detail);
    rvWisata = findViewById(R.id.rv_horizontal);
    }
    //set data
    private void lihatData(){
       String[] stringArray = getIntent().getStringArrayExtra(Key_Wisata);
        tvNamaWisata.setText(stringArray[0]);
        tvKeterangan.setText(stringArray[1]);
        tvLokasi.setText(stringArray[2]);
        String imgUrl = stringArray[3];
        Glide.with(this)
                    .load(imgUrl)
                    .apply(new RequestOptions().override(350, 550))
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imgPhotoWisata);
    }

    public void btn_petunjuk_arah(View view) {
        String[] stringArray = getIntent().getStringArrayExtra(Key_Wisata);
        String lokasi = stringArray[2];
        mapsIntenUri = Uri.parse("google.navigation:q=" + lokasi);
        mapsIntent = new Intent(Intent.ACTION_VIEW, mapsIntenUri);
        mapsIntent.setPackage(maps);
        if (mapsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapsIntent);
        } else {
            Toast.makeText(this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                    Toast.LENGTH_LONG).show();
        }
    }
    //back actionBar
    private void backToPrevious(){
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
    }
}
