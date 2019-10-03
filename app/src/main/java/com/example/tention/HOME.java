package com.example.tention;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HOME extends AppCompatActivity {
Button btnlogout;
FirebaseAuth mfirbase;
private FirebaseAuth.AuthStateListener mauthlistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnlogout=findViewById(R.id.logout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intomain = new Intent(HOME.this, MainActivity.class);
                startActivity(intomain);
            }
        });
    }
}
