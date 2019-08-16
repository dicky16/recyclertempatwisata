package com.travelisia.tempatwisata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
public class AboutMe extends AppCompatActivity {
    ImageView imgAbout;
    TextView nama, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        initComponent();
        setDAtaAbout();
        backToPrevious();
    }


    private void setDAtaAbout(){
       String mNama = "Dikky Setiawan";
       String mEmail = "setyawandicky88@gmail.com";
       String img = "https://1.bp.blogspot.com/-NGjZ8KL5gjk/XUki15IAnDI/AAAAAAAACYk/jGT1T3lxBWkG6Cl2RU-UwnMdZO8XphHUgCLcBGAs/s320/_MG_1363%2BDIKKY%2BTKJ1merah.jpg";
       nama.setText(mNama);
       email.setText(mEmail);
        Glide.with(this)
                .load(img)
                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.ic_launcher_background)
                .into(imgAbout);
    }
    private void initComponent(){
        imgAbout = findViewById(R.id.img_item_about);
        nama = findViewById(R.id.tv_nama_about);
        email = findViewById(R.id.tv_email_about);
    }
    private void backToPrevious(){
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
    }
}
