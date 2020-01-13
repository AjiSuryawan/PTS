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
import android.widget.LinearLayout;

public class Menu_Utama extends AppCompatActivity {
    private LinearLayout but;
    private Toolbar tollbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__utama);
        tollbar= findViewById(R.id.toolbar);
        setSupportActionBar(tollbar);
        but = findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Menu_Utama.this,List.class);
                startActivity(myIntent);
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
