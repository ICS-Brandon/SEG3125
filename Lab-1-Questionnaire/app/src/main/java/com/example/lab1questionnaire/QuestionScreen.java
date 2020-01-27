package com.example.lab1questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class QuestionScreen extends AppCompatActivity {

    String quizName;
    int quizPos;
    RadioButton opt1,opt2,opt3,opt4,opt5;
    TextView qView;
    ArrayList<Question> qList = new ArrayList<>();
    ArrayList<Choice> cList = new ArrayList<>();
    ArrayList<RadioButton> rBList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.question_layout);

        opt1 = findViewById(R.id.Option1);
        rBList.add(opt1);
        opt2 = findViewById(R.id.Option2);
        rBList.add(opt2);
        opt3 = findViewById(R.id.Option3);
        rBList.add(opt3);
        opt4 = findViewById(R.id.Option4);
        rBList.add(opt4);
        opt5 = findViewById(R.id.Option5);
        rBList.add(opt5);


        qView = findViewById(R.id.QViewQ);

        Intent receive = getIntent();
        quizName = receive.getStringExtra("quizName");
        quizPos = receive.getIntExtra("quizPos",0);

        opt1.setText(quizName);


        read_Json();
        //updateQuestion(0);
    }



    public void submitQuestion(View view){
        System.out.println("Button pressed");
    }


    public void updateQuestion(int qPos){
        Question updated = qList.get(qPos);

        int choiceCount = updated.cList.size();

        qView.setText(updated.title);

        disableButtons(choiceCount);

        for(int i = 0; i < choiceCount; i++){
            rBList.get(i).setText(updated.cList.get(i).body);
        }
    }


    public void disableButtons(int count){
        for(int i = 0;i<rBList.size();i++){
            if(i>count){
                rBList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    public void read_Json(){
        String json;
        try {
            InputStream is = getAssets().open("db.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");

            System.out.println("TEST");
            JSONObject jObj = new JSONObject(json);
            JSONArray jArray = jObj.getJSONArray("quizzes");
            JSONObject quizObj = jArray.getJSONObject(quizPos);
            JSONArray qArray = quizObj.getJSONArray("questions");

            for(int i = 0; i< qArray.length();i++){
                int qId = qArray.getJSONObject(i).getInt("id");
                String title = qArray.getJSONObject(i).getString("title");
                int answer = qArray.getJSONObject(i).getInt("answer");
                JSONObject cObj = qArray.getJSONObject(i);
                JSONArray cArray = cObj.getJSONArray("choices");
                cList.clear();
                /*for(int j = 0; j < cArray.length();i++){  Error in using forloop, need to fix
                    int cId = cArray.getJSONObject(j).getInt("id");
                    String body = cArray.getJSONObject(j).getString("body");
                    cList.add(new Choice(cId,body));
                }*/
                qList.add(new Question(qId,title,answer,cList));
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    class Question{
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
    }

    class Choice{
        public int id;
        public String body;

        public Choice(int id, String body){
            this.id = id;
            this.body = body;
        }
    }
}

