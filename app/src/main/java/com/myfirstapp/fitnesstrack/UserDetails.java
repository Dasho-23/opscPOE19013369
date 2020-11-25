package com.myfirstapp.fitnesstrack;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetails extends AppCompatActivity {
    //fields
    EditText _txtWeight, _txtInchesCm, _txtFootM, _txtGoalWeight,_txtCalorie,_txtDaily,_txtGoalCalInt;
    Button _btnSave, _btnMetric,_btnImperial, _btnOpenCam;
    String goalWeight,curWeight, dailyWeight, height,goalIntCal, calIntake,weightMesu;
    int meters, cm;
    int weight, gWeight;
    TextView _tvFootM, _tvInchesCm, _tvCurWeight, _tvGoalWeight;
    ImageView _imgCam;
    Details Detail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        //initialising the fields
        _txtWeight  =(EditText)findViewById(R.id.txtWeight);
        _txtInchesCm =(EditText)findViewById(R.id.txtInchesCm);
        _txtFootM  =(EditText)findViewById(R.id.txtFootM);
        _txtGoalWeight =(EditText)findViewById(R.id.txtGoalWeig);
        _txtCalorie  =(EditText)findViewById(R.id.txtCalorie);
        _txtDaily =(EditText)findViewById(R.id.txtDaily);
        _txtGoalCalInt = findViewById(R.id.txtGoalCal);

        _tvFootM = findViewById(R.id.tvFootmeter);
        _tvInchesCm =findViewById(R.id.tvInches);
        _tvCurWeight = findViewById(R.id.tvCurWeight);
        _tvGoalWeight = findViewById(R.id.tvGoalWeight);

        _imgCam = findViewById(R.id.imgViewTwo);

        _btnSave =(Button) findViewById(R.id.btnSave);
        _btnImperial = (Button) findViewById(R.id.btnImperial);
        _btnMetric = (Button) findViewById(R.id.btnMetric);
        _btnOpenCam = findViewById(R.id.btnCameraTwo);

        //Requesting camera permission
        if(ContextCompat.checkSelfPermission(UserDetails.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(UserDetails.this,
                    new String []{
                            Manifest.permission.CAMERA
                    },
                    100);
        }

        _btnOpenCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //Open camera
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 100);

            }
        });

        setContentView(R.layout.activity_user_details);
        //Switches the measurement system to Imperial and converting the details
        _btnImperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _tvCurWeight.setText("Pounds");
                _tvGoalWeight.setText("Pounds");
                _tvFootM.setText("Feet");
                _tvInchesCm.setText("Inches");
                meters= Integer.parseInt(_txtFootM.getText().toString());
                cm = Integer.parseInt(_txtInchesCm.getText().toString());
                meters = meters * 3 ;
                cm = cm / 2;
                _txtInchesCm.setText(cm);
                _txtFootM.setText(meters);

                weight= Integer.parseInt(_txtWeight.getText().toString());
                gWeight = Integer.parseInt(_txtGoalWeight.getText().toString());
                weight = weight *2;
                gWeight = gWeight *2;

                _tvCurWeight.setText(weight);
                _tvGoalWeight.setText(gWeight);




        //Switches the measurement system to Metric and converting teh details
        _btnMetric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _tvCurWeight.setText("Kgs");
                _tvGoalWeight.setText("Kgs");
                _tvFootM.setText("M");
                _tvInchesCm.setText("Cm");
                meters= Integer.parseInt(_txtFootM.getText().toString());
                cm = Integer.parseInt(_txtInchesCm.getText().toString());
                meters = meters / 3 ;
                cm = cm * 2;
                _txtInchesCm.setText(cm);
                _txtFootM.setText(meters);

                weight= Integer.parseInt(_txtWeight.getText().toString());
                gWeight = Integer.parseInt(_txtGoalWeight.getText().toString());
                weight = weight /2;
                gWeight = gWeight /2;

                _tvCurWeight.setText(weight);
                _tvGoalWeight.setText(gWeight);


            }

        });

_btnSave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mAuth = FirebaseAuth.getInstance();
        //Gets data from components
        goalWeight = _txtGoalWeight.getText().toString().trim()+_tvGoalWeight.getText().toString().trim();
        curWeight = _txtWeight.getText().toString().trim() ;
        dailyWeight = _txtDaily.getText().toString().trim();
        height = _txtFootM.getText().toString().trim()+_tvFootM.getText().toString().trim() + _txtInchesCm.getText().toString().trim()+_tvInchesCm.getText().toString().trim();
        goalIntCal = _txtGoalCalInt.getText().toString().trim();
        calIntake = _txtWeight.getText().toString().trim();
        weightMesu = _tvCurWeight.getText().toString().trim();
        //object for the details class
       Detail = new Details(goalWeight, curWeight, dailyWeight, height,goalIntCal,calIntake,weightMesu);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
       //Adds the data to the database
       myRef.child("Details").push().setValue(Detail);
       //takes the user to the Userinformation activity
        Intent intent = new Intent(UserDetails.this, Userinformation.class);
        startActivity(intent);

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
            _imgCam.setImageBitmap(captureImage);
        }
    }
}