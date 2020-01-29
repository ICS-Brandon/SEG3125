package com.example.lab1questionnaire;

import java.util.ArrayList;

public class Quiz {

    private int id;
    private String name;
    private int passNum;
    private ArrayList<Question> questions;
    private UserInfo userInfo;

    public Quiz(int i, String n, int p, ArrayList<Question> q, UserInfo u){
        this.id = i;
        this.name = n;
        this.passNum = p;
        this.questions = q;
        this.userInfo = u;
    }

    public void setId(int i ){this.id = i;}

    public int getId(){return this.id;}

    public void setName(String n){this.name = n;}

    public String getName(){return this.name;}

    public void setPassNum(int p){this.passNum = p;}

    public int getPassNum(){return this.passNum;}

    public void setQuestions(ArrayList<Question> q){this.questions = q;}

    public ArrayList<Question> getQuestions(){return this.questions;}

    public void setUserInfo(UserInfo i){this.userInfo = i;}

    public UserInfo getUserInfo(){return this.userInfo;}
}
