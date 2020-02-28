package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class Menu_Utama extends AppCompatActivity {
    private LinearLayout but;
    private LinearLayout but2;
    private Toolbar tollbar;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__utama);
        tollbar= findViewById(R.id.toolbar);
        profile= findViewById(R.id.profil);
        setSupportActionBar(tollbar);
        Glide
                .with(Menu_Utama.this)
                .load("http://localhost/latihan/sponge.jpg")
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(profile);
        but = findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Menu_Utama.this,List.class);
                startActivity(myIntent);
            }
        });
        but2 = findViewById(R.id.but2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent In = new Intent(Menu_Utama.this, webview.class);
                startActivity(In);
            }
        });

    }
    //Membuat menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option,menu);
        return true;
    }
    //Membuat pilihan menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.akun)
        {
            startActivity(new Intent(Menu_Utama.this,Akun.class));
        }
        if (item.getItemId()==R.id.setting)
        {
            startActivity(new Intent(Menu_Utama.this, pengaturan.class));
        }
        return true;
    }
}
