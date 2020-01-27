package com.example.lab1questionnaire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.util.JsonReader;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WelcomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Spinner quizSpin;
    List<String> quizList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        quizSpin = findViewById(R.id.QHomeSpinner);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        toggle.syncState();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);

        quizSpin.setOnItemSelectedListener(this);

        quizList.add("Select a quiz");

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
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navCreateQuiz:
                Toast.makeText(WelcomeScreen.this, "Create a Quiz Selected", Toast.LENGTH_SHORT).show();
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
        String name = getQuizName();
        int pos = getQuizPos();
        passable.putExtra("quizName",name);
        passable.putExtra("quizPos",getQuizPos());
        startActivity(passable);
    }

    public String getQuizName(){
        int pos = quizSpin.getSelectedItemPosition();
        String quizName = quizList.get(pos);
        return quizName;
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
}
