package com.example.usk_novafitiara;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilData extends AppCompatActivity implements ListView.OnItemClickListener {
    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);        getJSON();
    }

    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id_transaksi = jo.getString(Konfigurasi.TAG_IDTRANSAKSI);
                String id_keb = jo.getString(Konfigurasi.TAG_IDPKEB);
                String id_siswa = jo.getString(Konfigurasi.TAG_IDSISWA);
                String harga = jo.getString(Konfigurasi.TAG_HARGA);
                String tanggal_waktu = jo.getString(Konfigurasi.TAG_TANGGALWAKTU);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Konfigurasi.TAG_IDTRANSAKSI,id_transaksi);
                employees.put(Konfigurasi.TAG_IDPKEB,id_keb);
                employees.put(Konfigurasi.TAG_IDSISWA,id_siswa);
                employees.put(Konfigurasi.TAG_HARGA,harga);
                employees.put(Konfigurasi.TAG_TANGGALWAKTU,tanggal_waktu);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilData.this, list, R.layout.listdata,
                new String[]{Konfigurasi.TAG_IDTRANSAKSI,Konfigurasi.TAG_IDPKEB,Konfigurasi.TAG_IDSISWA,Konfigurasi.TAG_HARGA,Konfigurasi.TAG_TANGGALWAKTU},
                new int[]{R.id.id_transaksi,R.id.id_keb, R.id.id_siswa,R.id.harga,R.id.tanggal_waktu});

        listView.setAdapter(adapter);
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilData.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Konfigurasi.TRANSAKSIS_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailData.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Konfigurasi.TAG_IDTRANSAKSI).toString();
        intent.putExtra(Konfigurasi.EMP_ID,empId);
        startActivity(intent);
    }

}