package com.myfirstapp.fitnesstrack;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Details {
    //fields
    String goalWeight,curWeight, dailyWeight, height,goalIntCal, calIntake,weightMeasu;


    //empty constructor
    public Details() {

    }
//constructor with parameters
    public Details(String goalWeight, String curWeight, String dailyWeight, String height,String goalIntCal, String calIntake, String weightMeasu) {
        this.goalWeight = goalWeight;
        this.curWeight = curWeight;
        this.dailyWeight = dailyWeight;
        this.height = height;
        this.goalIntCal = goalIntCal;
        this.calIntake = calIntake;
        this.weightMeasu =weightMeasu;
    }
//getters and setters
    public String getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(String goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getCurWeight() {
        return curWeight;
    }

    public void setCurWeight(String curWeight) {
        this.curWeight = curWeight;
    }

    public String getDailyWeight() {
        return dailyWeight;
    }

    public void setDailyWeight(String dailyWeight) {
        this.dailyWeight = dailyWeight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGoalIntCall() {
        return goalIntCal;
    }

    public void setGoalIntCal(String goalIntCal) {
        this.calIntake = goalIntCal;
    }

    public String getCalIntake() {
        return calIntake;
    }

    public void setCalIntake(String calIntake) {
        this.calIntake = calIntake;
    }

    public String getWeightMeasu() {
        return weightMeasu;
    }

    public void setWeightMeasu(String weightMeasu) {
        this.weightMeasu = weightMeasu;
    }




}
