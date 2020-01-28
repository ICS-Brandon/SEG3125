package com.example.lab1questionnaire;

public class UserScore {
    private int correct, incorrect;
    private String currentQuiz;
    private int qNum;
    private int selection;

    public UserScore(String quiz){
        this.correct = 0;
        this.incorrect = 0;
        this.currentQuiz = quiz;
        this.qNum = 0;
        this.selection = 0;
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

}
