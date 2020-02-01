package com.example.lab1questionnaire;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

public class Settings extends AppCompatActivity {

    private GlobalVars gVars;
    private EditText passInput,qInput;
    private int pass, qNumber;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.settings);

        passInput = findViewById(R.id.passScore);
        qInput = findViewById(R.id.qNumber);

        passInput.addTextChangedListener(pWatcher);
        qInput.addTextChangedListener(qWatcher);

        gVars = (GlobalVars) getApplication();

        pass = gVars.getPassScore();
        qNumber = gVars.getQuestionCount();

        String qHint = "Current number of questions is: " + qNumber;
        String passHint = "Current score to pass is: "+ pass;

        passInput.setHint(passHint);
        qInput.setHint(qHint);


    }

    private TextWatcher pWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(pass != Integer.parseInt(passInput.getText().toString())){
                gVars.setPassScore(Integer.parseInt(passInput.getText().toString()));
            }
        }
    };

    private TextWatcher qWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(qNumber != Integer.parseInt(qInput.getText().toString())){
                gVars.setQuestionCount(Integer.parseInt(qInput.getText().toString()));
            }
        }
    };

    public void returnHome(View view){
        check_Valid();
        Intent homeScreen = new Intent(this,WelcomeScreen.class);
        startActivity(homeScreen);
    }

    public boolean check_Valid(){
        if(gVars.getPassScore() > gVars.getQuestionCount()){
            Toast.makeText(getApplicationContext(),"Error: Passing value is larger than question size, please change the values",Toast.LENGTH_SHORT).show();
            qInput.requestFocus();
            return false;
        } else if (gVars.getPassScore() <= 0 || gVars.getQuestionCount() <= 0) {
            Toast.makeText(getApplicationContext(),"Error: Cannot have values less than or equal to zero",Toast.LENGTH_SHORT).show();
            if(gVars.getQuestionCount() <=0)qInput.requestFocus();
            if(gVars.getPassScore()<=0)passInput.requestFocus();
        }



        return true;
    }
}
