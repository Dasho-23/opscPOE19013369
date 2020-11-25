package com.myfirstapp.fitnesstrack;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;


public class Goal {

//fields
    int currentW;
    String curWeight;
//getters and setters
    public int getCurrentW() {
        return currentW;
    }

    public void setCurrentW(int currentW) {
        this.currentW = currentW;
    }

    public String getCurWeight() {
        return curWeight;
    }

    public void setCurWeight(String curWeight) {
        this.curWeight = curWeight;
    }

//constructor
    public Goal(String curWeight, int currentW) {

        this.curWeight = curWeight;

        this.currentW = currentW;

    }




}
