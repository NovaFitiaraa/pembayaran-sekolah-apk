package com.example.usk_novafitiara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class KepalaSekolah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kepala_sekolah);

        CardView transaksi = findViewById(R.id.halamantransaksi);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KepalaSekolah.this,TampilData.class));
            }
        });
        CardView history = findViewById(R.id.halamanlogging);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KepalaSekolah.this,TampilHistory.class));
            }
        });
        CardView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KepalaSekolah.this,Login.class));
            }
        });
    }
}