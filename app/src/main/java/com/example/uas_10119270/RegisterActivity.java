package com.example.uas_10119270;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//10119270
//Albanna Rahmadani Sulistyo
//IF-7

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText mail, pass;
    private Button reg_btn;
    private TextView login_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        pass = findViewById(R.id.reg_pass);
        mail = findViewById(R.id.reg_email);
        reg_btn = findViewById(R.id.reg_btn);
        login_text = findViewById(R.id.reg_login);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void register() {
        String user =  mail.getText().toString().trim();
        String password =  pass.getText().toString().trim();
        if (user.isEmpty()) {
            mail.setError("Email tidak boleh kosong.");
        } if (password.isEmpty()) {
            pass.setError("Password tidak boleh kosong.");
        } else {
            auth.createUserWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Akun berhasil dibuat", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Akun gagal dibuat " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}