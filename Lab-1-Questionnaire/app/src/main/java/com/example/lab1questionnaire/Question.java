package com.example.lab1questionnaire;

import java.util.ArrayList;

public class Question {
    public int id;
    public String title;
    public int answer;
    public ArrayList<Choice> cList;

    public Question(int id, String title,int answer,ArrayList<Choice> cList){
        this.id = id;
        this.title = title;
        this.answer = answer;
        this.cList = cList;
    }

    public void setId(int i){
        this.id = i;
    }

    public int getId(){
        return this.id;
    }

    public void setTitle(String s){
        this.title = s;
    }

    public String getTitle(){
        return this.title;
    }

    public void setAnswer(int a){
        this.answer = a;
    }

    public int getAnswer(){
        return this.answer;
    }

    public void setcList(ArrayList<Choice> c){
        this.cList = c;
    }

    public ArrayList<Choice> getcList(){
        return this.cList;
    }
}
