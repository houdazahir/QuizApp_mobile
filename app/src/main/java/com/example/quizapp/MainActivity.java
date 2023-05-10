package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText etLogin, etPassword;
    Button bLogin;
    TextView tvRegister;
    TextView map;
    FirebaseAuth mAuth;
//    @Override
//    protected void onStart(){
//        super.onStart();
//        FirebaseUser user=mAuth.getCurrentUser();
//        if(user==null){
//            startActivity(new Intent(MainActivity.this, MainActivity.class));
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// fonction obligatoire dans une activite. elle sera executee au demarrage d'une application (voir cycle de vie d'une activité
        setContentView(R.layout.activity_main); //la fonction permet d associer un layout à un view
        etLogin = (EditText) findViewById(R.id.etMail);//r est une classe generée par le systeme qui regroupe les IDs
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        map = (TextView) findViewById(R.id.map);
        //firebase:
        mAuth=FirebaseAuth.getInstance();

        //Step 3: Association de listeners
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=etLogin.getText().toString();
                String password=etPassword.getText().toString();

                if(TextUtils.isEmpty(mail)){
                    etLogin.setError("Email cannot be empty");
                    etLogin.requestFocus();
                }else if (TextUtils.isEmpty(password)){
                    etLogin.setError("Password cannot be empty");
                    etLogin.requestFocus();
                } else{
                    mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                               Toast.makeText(MainActivity.this,"User logged in successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Quiz1.class));
                            }else {
                                Toast.makeText(MainActivity.this,"Log in Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                /*if (etLogin.getText().toString().equals("toto") && etPassword.getText().toString().equals("123")){
                    startActivity(new Intent(MainActivity.this, Quiz1.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login or password incorrect !",Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 4: Traitement
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 4: Traitement
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

    }
}
