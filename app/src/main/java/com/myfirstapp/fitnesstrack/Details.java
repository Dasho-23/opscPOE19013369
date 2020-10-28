package com.myfirstapp.fitnesstrack;

public class Details {
    //fields
    String goalWeight,curWeight, dailyWeight, height, calIntake;
   //empty constructor
    public Details() {

    }
//constructor with parameters
    public Details(String goalWeight, String curWeight, String dailyWeight, String height, String calIntake) {
        this.goalWeight = goalWeight;
        this.curWeight = curWeight;
        this.dailyWeight = dailyWeight;
        this.height = height;
        this.calIntake = calIntake;
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

    public String getCalIntake() {
        return calIntake;
    }

    public void setCalIntake(String calIntake) {
        this.calIntake = calIntake;
    }


}
