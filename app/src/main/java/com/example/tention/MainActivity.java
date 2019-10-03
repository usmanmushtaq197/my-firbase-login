package com.example.tention;

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

public class MainActivity extends AppCompatActivity {
EditText emaiid, password;
Button btsignup;
TextView tvsignin;
FirebaseAuth mfirbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emaiid=(EditText)findViewById(R.id.eml);
        password=(EditText)findViewById(R.id.pas);
        mfirbase=(FirebaseAuth) FirebaseAuth.getInstance();
        btsignup=(Button)findViewById(R.id.btsigup);
        tvsignin=(TextView)findViewById(R.id.tvsignin);

        btsignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String email=emaiid.getText().toString();
                String pwd=password.getText().toString();
                if (email.isEmpty())
                {
                    emaiid.setError("please enter email");
                    emaiid.requestFocus();

                }
                else if (pwd.isEmpty())
                {
                    password.setError("please enter password");
                    password.requestFocus();
                }
               else if (email.isEmpty()&& pwd.isEmpty()){
                    Toast.makeText(MainActivity.this, "feild are khali", Toast.LENGTH_SHORT).show();

                }
               else if (! email.isEmpty()&& pwd.isEmpty())
                {
                    mfirbase.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "unsucssfull signup try again", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intent = new Intent(MainActivity.this, HOME.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
               else {
                    Toast.makeText(MainActivity.this, "Error occurs!", Toast.LENGTH_SHORT).show();

                }
            }


        });
        tvsignin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
            }
        });
    }
}
