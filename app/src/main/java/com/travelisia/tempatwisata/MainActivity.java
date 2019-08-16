package com.travelisia.tempatwisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.travelisia.tempatwisata.Adapter.GridWisataAdapter;
import com.travelisia.tempatwisata.Adapter.WisataAdapter;
import com.travelisia.tempatwisata.Data.WisataData;
import com.travelisia.tempatwisata.Model.Wisata;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvWisata;
    private ArrayList<Wisata> listWisata = new ArrayList<>();
    public static final String Key_Wisata = "key_wisata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //initialize Arraylist
    rvWisata = findViewById(R.id.rv_main_wisata);
    rvWisata.setHasFixedSize(true);
    listWisata.addAll(WisataData.getListData());
    showRecyclerList();
    }
    private void showRecyclerList(){
        rvWisata.setLayoutManager(new LinearLayoutManager(this));
        WisataAdapter wisataAdapter = new WisataAdapter(listWisata);
        rvWisata.setAdapter(wisataAdapter);
        wisataAdapter.setOnItemWisataClick(new WisataAdapter.OnItemWisataClick() {
            @Override
            public void onItemClicked(Wisata data) {
                getDataWisata(data);
            }
        });
    }
    private void showRecyclerGrid(){
        rvWisata.setLayoutManager(new GridLayoutManager(this,2));
        GridWisataAdapter gridWisataAdapter = new GridWisataAdapter(listWisata);
        rvWisata.setAdapter(gridWisataAdapter);
        gridWisataAdapter.setOnItemWisataClick(new GridWisataAdapter.OnItemGridWisataClick() {
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
        Intent i = new Intent(MainActivity.this,LihatDetail.class);
        i.putExtra(Key_Wisata,new String[]{namaWisata,keterangan,lokasi,imgUrl});
        startActivity(i);

    }
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
     return super.onOptionsItemSelected(item);
    }
    public void setMode(int selectedMode){
        switch (selectedMode){
            case R.id.list_view:
                showRecyclerList();
                break;
            case R.id.grid_view:
                showRecyclerGrid();
                break;
            case R.id.about_me:
                startActivity(new Intent(this,AboutMe.class));
                break;
        }
    }
    //end menu

}
