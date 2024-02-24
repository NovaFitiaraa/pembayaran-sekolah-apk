package com.example.usk_novafitiara;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DetailData extends AppCompatActivity implements View.OnClickListener {

    private EditText edtid_keb;
    private EditText edtid_transaksi;
    private EditText edtid_siswa;
    private EditText edtid_operator;
    private EditText edttanggal_waktu;
    private EditText edttempat;
    private TextView edtsisa_bayar;
    private EditText edtharga;
    private EditText edtuang_bayar;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id_transaksi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        Intent intent = getIntent();

        id_transaksi = intent.getStringExtra(Konfigurasi.EMP_ID);

        edtid_transaksi = (EditText) findViewById(R.id.edtid_transaksi);
        edtid_keb = (EditText) findViewById(R.id.edtid_keb);
        edtid_siswa = (EditText) findViewById(R.id.edtid_siswa);
        edtid_operator = (EditText) findViewById(R.id.edtid_operator);
        edttanggal_waktu = (EditText) findViewById(R.id.edttanggal_waktu);
        edttempat = (EditText) findViewById(R.id.edttempat);
        edtharga = (EditText) findViewById(R.id.edtharga);
        edtuang_bayar = (EditText) findViewById(R.id.edtuang_bayar);
        edtsisa_bayar = (TextView) findViewById(R.id.edtsisa_bayar);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        edtid_transaksi.setText(id_transaksi);



        getEmployee();

    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailData.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.TRANSAKSIS_DETAILS,id_transaksi);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String id_keb = c.getString(Konfigurasi.TAG_IDPKEB);
            String id_siswa = c.getString(Konfigurasi.TAG_IDSISWA);
            String id_operator = c.getString(Konfigurasi.TAG_IDOPERATOR);
            String tanggal_waktu = c.getString(Konfigurasi.TAG_TANGGALWAKTU);
            String tempat = c.getString(Konfigurasi.TAG_TEMPAT);
            String harga = c.getString(Konfigurasi.TAG_HARGA);
            String uang_bayar = c.getString(Konfigurasi.TAG_UANGBAYAR);
            String sisa_bayar = c.getString(Konfigurasi.TAG_SISABAYAR);

            edtid_keb.setText(id_keb);
            edtid_siswa.setText(id_siswa);
            edtid_operator.setText(id_operator);
            edttanggal_waktu.setText(tanggal_waktu);
            edttempat.setText(tempat);
            edtharga.setText(harga);
            edtuang_bayar.setText(uang_bayar);
            edtsisa_bayar.setText(sisa_bayar);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateEmployee(){
        final String id_keb = edtid_keb.getText().toString().trim();
        final String id_siswa = edtid_siswa.getText().toString().trim();
        final String id_operator = edtid_operator.getText().toString().trim();
        final String tempat = edttempat.getText().toString().trim();
        final String harga = edtharga.getText().toString().trim();
        final String uang_bayar = edtuang_bayar.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailData.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailData.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_TRANSAKSI_IDTRANSAKSI,id_transaksi);
                hashMap.put(Konfigurasi.KEY_TRANSAKSI_IDKEB, id_keb);
                hashMap.put(Konfigurasi.KEY_TRANSAKSI_IDSISWA,id_siswa);
                hashMap.put(Konfigurasi.KEY_TRANSAKSI_IDOPERATOR,id_operator);
                hashMap.put(Konfigurasi.KEY_TRANSAKSI_TEMPAT,tempat);
                hashMap.put(Konfigurasi.KEY_TRANSAKSI_HARGA,harga);
                hashMap.put(Konfigurasi.KEY_TRANSAKSI_UANGBAYAR,uang_bayar);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.TRANSAKSIS_UPDATE,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailData.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailData.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.TRANSAKSIS_HAPUS, id_transaksi);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah kamu yakin ingin menghapus data  ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(DetailData.this,TampilData.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }

}