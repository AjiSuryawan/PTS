package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
    private ImageView tambah_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        tambah_data = (findViewById(R.id.tambah_data));
        tambah_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent (List.this, tambah_data.class);
                startActivityForResult(myIntent, 23);
            }
        });
     //   addData();
        getDataFromRemote();
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


    }

    private void getDataFromRemote() {
        AndroidNetworking.get(BaseURL.url + "getdata.php")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini

                        mahasiswaArrayList = new ArrayList<>();
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("result");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
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

                            adapter = new MahasiswaAdapter(mahasiswaArrayList, new MahasiswaAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Intent in = new Intent(List.this, EditData.class);
                                    in.putExtra("id", mahasiswaArrayList.get(position).getId());
                                    in.putExtra("nama", mahasiswaArrayList.get(position).getNama());
                                    in.putExtra("pesanan", mahasiswaArrayList.get(position).getPesanan());
                                    in.putExtra("no-hp", mahasiswaArrayList.get(position).getNo_hp());
                                    in.putExtra("alamat", mahasiswaArrayList.get(position).getAlamat());
                                    startActivityForResult(in, 23);
                                }

                                @Override
                                public void test() {

                                }
                            });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 23 && data.getStringExtra("refresh") != null) {
            //refresh list
            getDataFromRemote();
            Toast.makeText(this, "hihihihi", Toast.LENGTH_SHORT).show();

        }
    }

}

