package com.example.usk_novafitiara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class InputData extends AppCompatActivity {

    EditText eid_keb, eid_siswa, eid_operator, etempat, eharga, euang_bayar;
    Button buttonAdd, buttonDaftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        Button daftartransaksi =findViewById(R.id.buttonView);
        daftartransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputData.this,TampilData.class));
            }
        });

        eid_keb = findViewById(R.id.id_keb);
        eid_siswa = findViewById(R.id.id_siswa);
        eid_operator = findViewById(R.id.id_operator);
        etempat = findViewById(R.id.tempat);
        eharga = findViewById(R.id.harga);
        euang_bayar = findViewById(R.id.uang_bayar);
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }

    public void insertData(){
        String id_keb = eid_keb.getText().toString();
        String id_siswa = eid_siswa.getText().toString();
        String id_operator = eid_operator.getText().toString();
        String tempat = etempat.getText().toString();
        String harga= eharga.getText().toString();
        String uang_bayar = euang_bayar.getText().toString();
        class InsertData extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(InputData.this, "Menambahkan data..", "Mohon tunggu", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String status = jsonObject.getString("status");
                    String result = jsonObject.getString("result");
                    Toast.makeText(InputData.this, result, Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(InputData.this, "Catch", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                HashMap<String, String> map = new HashMap<>();
                map.put(Konfigurasi.KEY_TRANSAKSI_IDKEB, id_keb);
                map.put(Konfigurasi.KEY_TRANSAKSI_IDSISWA, id_siswa);
                map.put(Konfigurasi.KEY_TRANSAKSI_IDOPERATOR, id_operator);
                map.put(Konfigurasi.KEY_TRANSAKSI_TEMPAT, tempat);
                map.put(Konfigurasi.KEY_TRANSAKSI_HARGA, harga);
                map.put(Konfigurasi.KEY_TRANSAKSI_UANGBAYAR, uang_bayar);


                String s = rh.sendPostRequest(Konfigurasi.TRANSAKSIS_ADD, map);
                return s;
            }

        }
        InsertData id = new InsertData();
        id.execute();
    }
}