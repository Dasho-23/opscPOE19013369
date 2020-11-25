package com.myfirstapp.fitnesstrack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Userinformation extends AppCompatActivity {
    //Fields
    EditText _txtGW,_txtW,_txtH,_txtDWC,_txtCI,_txtGIC;
    Button _btnEdit,_btnView,_btnOpen, _btnChart;
    String goalWeight,curWeight, dailyWeight, height, calIntake,goalIntake;
    Details Detail;
    ImageView imgView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinformation);

        mAuth = FirebaseAuth.getInstance();
        //initialising the fields
        _txtGW  =(EditText)findViewById(R.id.txtEditGoal);
        _txtW =(EditText)findViewById(R.id.txtEditWeight);
        _txtH =(EditText)findViewById(R.id.txtEditHeight);
        _txtDWC =(EditText)findViewById(R.id.txtEditDaily);
        _txtCI  =(EditText)findViewById(R.id.txtEditCalorie);
        _txtGIC = findViewById(R.id.txtGoalCalorie);

        imgView =findViewById(R.id.imgView);


        _btnEdit =(Button) findViewById(R.id.btnEdit);
        _btnView =(Button) findViewById(R.id.btnView);
        _btnOpen = findViewById(R.id.btnOpen);
        _btnChart = findViewById(R.id.btnChart);

        //Requesting camera permission
        if(ContextCompat.checkSelfPermission(Userinformation.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Userinformation.this,
                    new String []{
                            Manifest.permission.CAMERA
                    },
                    100);
        }


        _btnOpen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        _btnChart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets the data for the chart before taking the user to the chart page
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
                goalWeight = Detail.getCurWeight();
                curWeight = Detail.getCurWeight();
                dailyWeight = Detail.getDailyWeight();
                height = Detail.getHeight();
                goalIntake = Detail.getGoalIntCall();
                calIntake = Detail.getCalIntake();
                //objet of details class
                Detail = new Details(goalWeight, curWeight, dailyWeight, height,goalIntake,calIntake,Detail.getWeightMeasu());

                //takes the user to the chart page
                Intent intent = new Intent(Userinformation.this, Chart.class);
                startActivity(intent);

            }
        });


        _btnEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
                 //Updates the data to whatever the user changes it to
                goalWeight = _txtGW.getText().toString().trim();
                curWeight = _txtW.getText().toString().trim();
                dailyWeight = _txtDWC.getText().toString().trim();
                height = _txtH.getText().toString().trim();
                goalIntake = _txtGIC.getText().toString().trim();
                calIntake = _txtCI.getText().toString().trim();
                Detail = new Details(goalWeight, curWeight, dailyWeight, height,goalIntake,calIntake,Detail.getWeightMeasu());

                myRef.child("Details").setValue(Detail);


            }
        });
        _btnView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
                myRef.child("Details").addValueEventListener(new ValueEventListener() {
                    //Displays the user's information
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Detail = snapshot.getValue(Details.class);
                        _txtH.setText(Detail.getHeight());
                        _txtW.setText(Detail.getCurWeight());
                        _txtGW.setText(Detail.getGoalWeight());
                        _txtDWC.setText(Detail.getDailyWeight());
                        _txtGIC.setText(Detail.getGoalIntCall());
                        _txtCI.setText(Detail.getCalIntake());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Userinformation.this, error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //Get Captured image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //set captured image to imageview
            imgView.setImageBitmap(captureImage);
        }
    }
}