package com.example.usk_novafitiara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

public class TampilHistory extends AppCompatActivity implements ListView.OnItemClickListener{

    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_history);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);        getJSON();
    }

    private void showEmployee() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id_logtrans = jo.getString(Konfigurasi.TAG_IDLOGTRANS);
                String id_transaksi = jo.getString(Konfigurasi.TAG_IDTRANSLOG);
                String waktu = jo.getString(Konfigurasi.TAG_WAKTU);
                String status = jo.getString(Konfigurasi.TAG_STATUS);


                HashMap<String, String> employees = new HashMap<>();
                employees.put(Konfigurasi.TAG_IDLOGTRANS, id_logtrans);
                employees.put(Konfigurasi.TAG_IDTRANSLOG, id_transaksi);
                employees.put(Konfigurasi.TAG_WAKTU, waktu);
                employees.put(Konfigurasi.TAG_STATUS, status);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilHistory.this, list, R.layout.listhistory,
                new String[]{Konfigurasi.TAG_IDLOGTRANS, Konfigurasi.TAG_IDTRANSLOG, Konfigurasi.TAG_WAKTU, Konfigurasi.TAG_STATUS},
                new int[]{R.id.id_logtrans, R.id.id_transaksi, R.id.waktu, R.id.status});

        listView.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilHistory.this, "Mengambil Data", "Mohon Tunggu...", false, false);
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
                String s = rh.sendGetRequest(Konfigurasi.HISTORY_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}