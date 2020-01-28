package com.example.lab1questionnaire;

public class Choice {
    public int id;
    public String body;

    public Choice(int id, String body){
        this.id = id;
        this.body = body;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setBody(String body){
        this.body = body;
    }

    public String getBody(){
        return this.body;
    }
}
