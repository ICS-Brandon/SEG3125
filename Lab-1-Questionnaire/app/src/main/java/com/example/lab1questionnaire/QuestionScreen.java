package com.example.lab1questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuestionScreen extends AppCompatActivity {

    private Button submitButton;
    private int quizPos,passScore,questionMax;
    private RadioButton opt1,opt2,opt3,opt4,opt5;
    private RadioGroup rGroup;
    private TextView qView,qRight,qWrong,viewQNum;
    private ArrayList<Question> questionList = new ArrayList<>();
    private ArrayList<RadioButton> rBList = new ArrayList<>();
    private UserInfo user;
    private Quiz currentQuiz;
    private GlobalVars gVars;

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

        rGroup = findViewById(R.id.QViewA);


        qView = findViewById(R.id.QViewQ);
        qRight = findViewById(R.id.QViewRight);
        qWrong = findViewById(R.id.QViewWrong);
        viewQNum = findViewById(R.id.QViewQNum);
        submitButton = findViewById(R.id.SubmitButton);

        gVars = (GlobalVars) getApplication();
        passScore = gVars.getPassScore();
        questionMax = gVars.getQuestionCount();

        Intent receive = getIntent();
        quizPos = receive.getIntExtra("quizPos",0);
        init_Quiz();
    }

    public void updateQuestion(int qPos){

        viewQNum.setText("Question #"+String.valueOf(user.getQNum()));

        Question updated = questionList.get(qPos);

        int choiceCount = updated.cList.size();

        qView.setText(updated.title);

        disableButtons(choiceCount);

        for(int i = 0; i < choiceCount; i++){
            rBList.get(i).setText(updated.cList.get(i).body);
        }


    }


    public void disableButtons(int count){
        for(int i = 0;i<rBList.size();i++){
            if(i>=count){
                rBList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    public void init_Quiz(){

        String json;
        try {
            InputStream is = getAssets().open("db.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");

            JSONObject jObj = new JSONObject(json);
            JSONArray jArray = jObj.getJSONArray("quizzes");
            JSONObject quizObj = jArray.getJSONObject(quizPos);
            JSONArray qArray = quizObj.getJSONArray("questions");

            for(int i = 0; i< qArray.length();i++){
                int qId = qArray.getJSONObject(i).getInt("id");
                String title = qArray.getJSONObject(i).getString("title");
                int answer = qArray.getJSONObject(i).getInt("answer");
                JSONObject qobj = qArray.getJSONObject(i);
                JSONArray cArray = qobj.getJSONArray("options");
                ArrayList<Choice> cList = new ArrayList<>();
                for(int j = 0; j < cArray.length();j++){
                    int id = cArray.getJSONObject(j).getInt("id");
                    String body = cArray.getJSONObject(j).getString("body");
                    cList.add(new Choice(id,body));
                }
                questionList.add(new Question(qId,title,answer,cList));
            }


            if(questionMax > questionList.size()){
                questionMax = questionList.size();
                Toast.makeText(getApplicationContext(),"Specific question count is too big, using all questions",Toast.LENGTH_SHORT).show();
            }
            else if(passScore > questionMax){
                if((questionMax&1)==0){
                    passScore = questionMax/2;
                }
                else{
                    passScore = questionMax/2 +1;
                }
                Toast.makeText(getApplicationContext(),"Specified passing score is too big, using default value of 50%",Toast.LENGTH_SHORT).show();
            }

            if(questionMax > 0 && questionMax <= questionList.size()){
                if(passScore > 0 && passScore <= questionMax){
                    user = new UserInfo(quizObj.getString("name"),passScore);
                    currentQuiz = new Quiz(quizObj.getInt("id"),quizObj.getString("name"),passScore,questionList,user);
                }
                else{
                    if((questionMax&1)==0){
                        passScore = questionMax/2;
                    }
                    else{
                        passScore = questionMax/2 +1;
                    }
                    user = new UserInfo(quizObj.getString("name"),passScore);
                    currentQuiz = new Quiz(quizObj.getInt("id"),quizObj.getString("name"),passScore,questionList,user);
                }
            }
            else if(questionMax <= 0 || questionMax > questionList.size()){
                questionMax = questionList.size();
                if(passScore > 0 && passScore <= questionMax){
                    user = new UserInfo(quizObj.getString("name"),passScore);
                    currentQuiz = new Quiz(quizObj.getInt("id"),quizObj.getString("name"),passScore,questionList,user);
                }
                else{
                    if((questionMax&1)==0){
                        passScore = questionMax/2;
                    }
                    else{
                        passScore = questionMax/2 +1;
                    }
                    user = new UserInfo(quizObj.getString("name"),passScore);
                    currentQuiz = new Quiz(quizObj.getInt("id"),quizObj.getString("name"),passScore,questionList,user);
                }
            }
            updateQuestion(0);

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void op1Select(View view){
        user.setSelection(1);
    }

    public void op2Select(View view){
        user.setSelection(2);
    }

    public void op3Select(View view){
        user.setSelection(3);
    }

    public void op4Select(View view){
        user.setSelection(4);
    }

    public void op5Select(View view){
        user.setSelection(5);
    }

    public void submitQuestion(View view){
        int sol = questionList.get(quizPos).getAnswer();
        if(user.getSelection()== sol){
            Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_SHORT).show();
            user.setCorrect(user.getCorrect()+1);
            qRight.setText(String.valueOf(user.getCorrect()));
        }
        else{
            Toast.makeText(getApplicationContext(),"Incorrect. The correct answer was #"+sol,Toast.LENGTH_SHORT).show();
            user.setIncorrect(user.getIncorrect()+1);
            qWrong.setText(String.valueOf(user.getIncorrect()));
        }

        quizPos++;

        if(quizPos < questionMax){
            user.setqNum(user.getQNum()+1);
            rGroup.clearCheck();
            updateQuestion(quizPos);
        }
        else{

            Toast.makeText(getApplicationContext(),String.valueOf(passScore),Toast.LENGTH_SHORT).show();
            double percentage = ((double)user.getCorrect()/(double)questionMax)*100;
            Intent finishedScreen = new Intent(this,QuizFinished.class);
            finishedScreen.putExtra("percent",percentage);
            if(user.getCorrect() >= passScore){
                finishedScreen.putExtra("passed",true);
            }
            else{
                finishedScreen.putExtra("passed",false);
            }
            startActivity(finishedScreen);

        }

    }
}

