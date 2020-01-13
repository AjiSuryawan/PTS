package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.os.Bundle;

public class List extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<model> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        addData();

        recyclerView = (RecyclerView) findViewById(R.id.list);

        adapter = new MahasiswaAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new model("Dimas Maulana", "1414370309", "123456789"));
        mahasiswaArrayList.add(new model("Fadly Yonk", "1214234560", "987654321"));
        mahasiswaArrayList.add(new model("Ariyandi Nugraha", "1214230345", "987648765"));
        mahasiswaArrayList.add(new model("Aham Siswana", "1214378098", "098758124"));
    }
    }

