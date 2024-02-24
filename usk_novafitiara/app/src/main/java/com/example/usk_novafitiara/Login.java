package com.example.usk_novafitiara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    String admin = "admin";
    String pwadmin = "admin";
    String operator = "operator";
    String pwoperator = "operator";
    String kepala_sekolah = "kepala_sekolah";
    String pwkepala_sekolah = "kepala_sekolah";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText txtuser = findViewById(R.id.username);
        EditText txtpass = findViewById(R.id.password);
        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtuser.getText().toString().equalsIgnoreCase(operator)&& txtpass.getText().toString().equalsIgnoreCase(pwoperator)){
                    startActivity(new Intent(Login.this, Operator.class));
                }else if (txtuser.getText().toString().equalsIgnoreCase(admin)&& txtpass.getText().toString().equalsIgnoreCase(pwadmin)){
                    startActivity(new Intent(Login.this,Admin.class));
                }else if (txtuser.getText().toString().equalsIgnoreCase(kepala_sekolah)&& txtpass.getText().toString().equalsIgnoreCase(pwkepala_sekolah)){
                    startActivity(new Intent(Login.this,KepalaSekolah.class));
                }else {
                    Toast.makeText(Login.this,"Username/Password Salah",Toast.LENGTH_SHORT).show();
                };
            }
        });


    }
}