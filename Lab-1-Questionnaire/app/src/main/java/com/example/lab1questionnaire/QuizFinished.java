package com.example.lab1questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class QuizFinished extends AppCompatActivity {

    private double percentage;
    private boolean passed;
    private TextView grade;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.quiz_finished);

        Intent receive = getIntent();
        percentage = receive.getDoubleExtra("percent",0);
        passed = receive.getBooleanExtra("passed",false);

        drawerLayout = findViewById(R.id.FinishDrawer);
        grade = findViewById(R.id.GradeView);
        navigationView = findViewById(R.id.FinishView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });

        String percent = roundTwo(percentage);

        String fail = "Sorry, but you failed the test. You got " + percent + "% which doesn't make the cut, better luck next time";
        String pass = "Congratulations you passed the test! You got " + percent + "%! Good job.";

        if(passed){
            grade.setText(pass);
        }
        else if(!passed){
            grade.setText(fail);
        }

    }

    public String roundTwo(double percentage){
        return String.format("%.2f",percentage);
    }

    public void backHome(View view){
        Intent returnIntent = new Intent(this,WelcomeScreen.class);
        startActivity(returnIntent);
    }
}
