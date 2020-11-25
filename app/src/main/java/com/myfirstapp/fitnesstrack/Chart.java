package com.myfirstapp.fitnesstrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Chart extends AppCompatActivity {

//fields
    BarChart barchart;
    IBarDataSet bardata;
    ArrayList<BarEntry> barentries;
    ArrayList<String> labelnames;
    ArrayList<Goal> Goals = new ArrayList<>();
    Details d = new Details();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart2);
        barchart = findViewById(R.id.barChart);
        //create new object of bar entries and labelnames
        barentries = new ArrayList<>();
        labelnames = new ArrayList<>();
       //calls on the method to fill the goals arraylist
        fillGoals();
// populates the barentries arraylist and label names arraylisy
       for(int i= 0; i< Goals.size();i++){
           String label = Goals.get(i).getCurWeight();
           int amt = Goals.get(i).getCurrentW();
           barentries.add(new BarEntry( i, amt));
           labelnames.add(label);
       }
       //formats teh bar chart with headings and labels
        BarDataSet bardataset = new BarDataSet(barentries,"Currents and goals");
       bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("GOALS");
        barchart.setDescription(description);
        BarData barData = new BarData((bardataset));
        barchart.setData(barData);

        //x axis format
        XAxis xAxis = barchart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelnames));

        //set positions of labels
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelnames.size());
        xAxis.setLabelRotationAngle(270);
        barchart.animateY(2000);
        barchart.invalidate();


    }
    //Methos that fill the goals array list with the current weight , goal weight , current calorie intake and goal calorie intake
    //using the goal class
    private void fillGoals(){
        Goals.clear();
        Goals.add(new Goal ("Current weight",Integer.parseInt(d.curWeight)));
        Goals.add(new Goal ("Goal weight",Integer.parseInt(d.goalWeight)));
        Goals.add(new Goal ("Current calorie intake",Integer.parseInt(d.calIntake)));
        Goals.add(new Goal ("Goal weight",Integer.parseInt(d.goalIntCal)));


    }
}