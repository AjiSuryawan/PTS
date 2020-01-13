package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private Button btnRegis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    etEmail = findViewById(R.id.user);
    etPassword = findViewById(R.id.password);

    btnRegis = findViewById(R.id.Register);
        btnRegis.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = etEmail.getText().toString();
            String passeord = etPassword.getText().toString();

            if (user.isEmpty()){
                Toast.makeText(Register.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
            if (passeord.isEmpty()){
                Toast.makeText(Register.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            AndroidNetworking.post(BaseURL.url+"api.php")
                    .addBodyParameter("user", user)
                    .addBodyParameter("passwords", passeord)
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject hasil = response.getJSONObject("hasil");
                                boolean sukses = hasil.getBoolean("respon");
                                if (sukses){
                                    Toast.makeText(Register.this, "Register Suskses", Toast.LENGTH_SHORT).show();
                                    Intent myIntent = new Intent(Register.this,MainActivity.class);
                                    startActivity(myIntent);
                                } else {
                                    Toast.makeText(Register.this, "Register gagal", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e){
                                e.printStackTrace();
                                System.out.println("ttttt" + e.getMessage());
                                Toast.makeText(Register.this, "Login gagal", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            System.out.println("ttttt" + anError);
                            System.out.println("ttttt" + anError.getErrorBody());
                            System.out.println("ttttt" + anError.getErrorDetail());
                            System.out.println("ttttt" + anError.getResponse());
                            System.out.println("ttttt" + anError.getErrorCode());

                            Toast.makeText(Register.this, "Login gagal", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    });
}
}

