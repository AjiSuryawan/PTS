package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class List extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<model> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        addData();
    }

    void addData() {
// offline
//        mahasiswaArrayList = new ArrayList<>();
//        mahasiswaArrayList.add(new model("1","Dimas Maulana", "Kudus", "081222333444","HP"));
//        mahasiswaArrayList.add(new model("2","Budi", "Jepara", "082333444555","Laptop"));
//        mahasiswaArrayList.add(new model("3","Ani", "Pati", "083111222333","TV"));
//
//
//        adapter = new MahasiswaAdapter(mahasiswaArrayList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(List.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

        //get data online
                AndroidNetworking.get(BaseURL.url + "getdata.php")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: "+response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini

                        mahasiswaArrayList = new ArrayList<>();
                        try {
                            Log.d("hasiljson", "onResponse: "+response.toString());
                            JSONArray jsonArray = response.getJSONArray("result");
                            Log.d("hasiljson2", "onResponse: "+jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                model model = new model(
                                        jsonObject.getString("id"),
                                        jsonObject.getString("nama"),
                                        jsonObject.getString("alamat"),
                                        jsonObject.getString("No_hp"),
                                        jsonObject.getString("pesanan")
                                );
                                mahasiswaArrayList.add(model);
                            }

                            adapter = new MahasiswaAdapter(mahasiswaArrayList);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(List.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

    }

}

