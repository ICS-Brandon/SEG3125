package com.example.lab1questionnaire;

import android.app.Application;

public class GlobalVars extends Application {

    private int passScore = 0;
    private int questionCount = 0;

    public void setPassScore(int i){passScore = i;}

    public void setQuestionCount(int i ){questionCount = i;}

    public int getPassScore(){return passScore;}

    public int getQuestionCount(){return questionCount;}

}
