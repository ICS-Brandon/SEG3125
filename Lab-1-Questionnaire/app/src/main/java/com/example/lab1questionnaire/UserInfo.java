package com.example.lab1questionnaire;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int correct, incorrect;
    private String currentQuiz;
    private int qNum;
    private int selection;
    private int passNum;

    public UserInfo(String quiz, int pNum){
        this.correct = 0;
        this.incorrect = 0;
        this.currentQuiz = quiz;
        this.qNum = 0;
        this.selection = 0;
        this.passNum = pNum;
    }

    public void setCorrect(int c){
        this.correct = c;
    }

    public int getCorrect(){
        return this.correct;
    }

    public void setIncorrect(int i){
        this.incorrect = i;
    }

    public int getIncorrect(){
        return this.incorrect;
    }

    public void setCurrentQuiz(String q){
        this.currentQuiz = q;
    }

    public String getCurrentQuiz(){
        return this.currentQuiz;
    }

    public void setqNum(int q){
        this.qNum = q;
    }

    public int getQNum(){
        return this.qNum;
    }

    public void setSelection(int s){
        this.selection = s;
    }

    public int getSelection(){
        return this.selection;
    }

    public void setPassNum(int p){this.passNum = p;}

    public int getPassNum(){return this.passNum;}

}
