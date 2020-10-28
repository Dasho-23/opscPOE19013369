package com.myfirstapp.fitnesstrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //fields
 EditText _txtUsername, _txtPassword;
 Button _btnLogin, _btnReg;
 String email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialising the fields
        _txtUsername  =(EditText)findViewById(R.id.txtUsername);
        _txtPassword =(EditText)findViewById(R.id.txtPassword);
        _btnLogin =(Button) findViewById(R.id.btnLogin);
        _btnReg = (Button) findViewById(R.id.btnReg);

        _btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the user's data
                email = _txtUsername.getText().toString().trim();
                password = _txtPassword.getText().toString().trim();
                mAuth = FirebaseAuth.getInstance();
                //adding the user to the database
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText( MainActivity.this,"User"+
                                            mAuth.getCurrentUser().getEmail()+" was created successfully",Toast.LENGTH_SHORT).show();}

                            }
                        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //takes the user to the user details page
                        Intent intent = new Intent(MainActivity.this, Userinformation.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //displays message error
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = _txtUsername.getText().toString().trim();
                password = _txtPassword.getText().toString().trim();
                mAuth = FirebaseAuth.getInstance();
                //validates the users info if its in the database
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText( MainActivity.this,"User"+
                                            mAuth.getCurrentUser().getEmail()+" was logged in successfully",Toast.LENGTH_SHORT).show();}

                            }


                        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //takes the user to the user details page
                        Intent intent = new Intent(MainActivity.this, Userinformation.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //displays error message
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }



        });
        

    }


}