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
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    EditText emaiid, password;
    Button btsignin;
    TextView tvsignup;
    FirebaseAuth mfirbase;
    private  FirebaseAuth.AuthStateListener mAuthstatelistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emaiid=(EditText)findViewById(R.id.eml);
        password=(EditText)findViewById(R.id.pas);
        mfirbase=(FirebaseAuth) FirebaseAuth.getInstance();
        btsignin=(Button)findViewById(R.id.btsigup);
        tvsignup=(TextView)findViewById(R.id.tvsignin);
        mAuthstatelistener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mfirebaseUser=mfirbase.getCurrentUser();
                if(mfirebaseUser!=null){
                    Toast.makeText(login.this, "you are login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, HOME.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(login.this, "plez  login", Toast.LENGTH_SHORT).show();
                }
            }
        };
 btsignin.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         {
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
                 Toast.makeText(login.this, "feild are khali", Toast.LENGTH_SHORT).show();

             }
             else if (! email.isEmpty()&& pwd.isEmpty())
             {
                 mfirbase.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(!task.isSuccessful())
                         {
                             Toast.makeText(login.this, "login error", Toast.LENGTH_SHORT).show();
                         }
                         else {

                          Intent inttohome= new Intent(login.this, HOME.class);
                          startActivity(inttohome);
                         }
                     }
                 });
             }
             else {
                 Toast.makeText(login.this, "Error occurs!", Toast.LENGTH_SHORT).show();

             }
         }




     }
      });
 tvsignup.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Intent signup= new Intent(login.this, MainActivity.class);
         startActivity(signup);
     }
 });
    }

    @Override
    protected  void onStart(){
        super.onStart();
        mfirbase.addAuthStateListener(mAuthstatelistener);
    }

}
