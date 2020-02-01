package com.example.lab1questionnaire;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WelcomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Spinner quizSpin;
    private List<String> quizList = new ArrayList<>();
    private TextView quizInfo;
    private GlobalVars gVars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        quizSpin = findViewById(R.id.QHomeSpinner);
        quizInfo = findViewById(R.id.quizInfo);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        toggle.syncState();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);

        quizSpin.setOnItemSelectedListener(this);

        quizList.add("Select a quiz");

        gVars = (GlobalVars) getApplication();

        display_Quiz_Info();
        read_quizzes();

        ArrayAdapter<String> quizAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,quizList){
          @Override
          public boolean isEnabled(int position){
              if(position == 0){
                  return false;
              }else
                  return true;
          }
          @Override
            public View getDropDownView(int position, View convertVeiew, ViewGroup parent){
              View view = super.getDropDownView(position,convertVeiew,parent);
              TextView v = (TextView) view;
              if(position == 0){
                  v.setTextColor(Color.GRAY);
              }else
                  v.setTextColor(Color.BLACK);

              return view;
          }
        };
        quizAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quizSpin.setAdapter(quizAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navHome:
                Toast.makeText(WelcomeScreen.this, "You are already on the homepage", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navSettings:
                Intent settings = new Intent(this,Settings.class);
                startActivity(settings);
                break;
            case R.id.navContact:
                Toast.makeText(WelcomeScreen.this, "Contact us Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navAbout:
                Toast.makeText(WelcomeScreen.this, "About us Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navHelp:
                Toast.makeText(WelcomeScreen.this, "Help Selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    public void startQuiz(View view){
        Intent passable = new Intent(this,QuestionScreen.class);
        passable.putExtra("quizPos",getQuizPos());
        startActivity(passable);
    }

    public int getQuizPos(){
        int pos = quizSpin.getSelectedItemPosition()-1;
        return pos;
    }

    public void read_quizzes(){
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

            for(int i =0; i<jArray.length();i++){

                JSONObject quiz = jArray.getJSONObject(i);
                quizList.add(quiz.getString("name"));
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void display_Quiz_Info(){
        String defaultVals = "Current passing score is half of the questions in the quiz";
        String defaultPass = "Current passing score is: " + gVars.getPassScore() + " questions";
        String defaultQs = "Current passing score is: " + gVars.getQuestionCount()/2 + " questions";
        String customVals = "Current passing score is: " + gVars.getPassScore() + " of " + gVars.getQuestionCount() + " questions";

        if(gVars.getPassScore()==0 && gVars.getQuestionCount() ==0){
            quizInfo.setText(defaultVals + "\n(Change values in settings)");
        }
        else if(gVars.getPassScore() != 0 && gVars.getQuestionCount() == 0){
            quizInfo.setText(defaultPass + "\n(Change values in settings)");
        }
        else if(gVars.getPassScore() == 0 && gVars.getQuestionCount() != 0){
            quizInfo.setText(defaultQs + "\n(Change values in settings)");
        }
        else if(gVars.getPassScore()!=0 && gVars.getQuestionCount()!=0){
            quizInfo.setText(customVals + "\n(Change values in settings");
        }

    }
}
