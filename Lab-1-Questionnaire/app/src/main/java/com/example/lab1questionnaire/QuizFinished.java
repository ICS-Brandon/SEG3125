package com.example.lab1questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Set;

public class QuizFinished extends AppCompatActivity {

    private double percentage;
    private int right, wrong;
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
        right = receive.getIntExtra("right",0);
        wrong = receive.getIntExtra("wrong",0);

        drawerLayout = findViewById(R.id.FinishDrawer);
        grade = findViewById(R.id.GradeView);
        navigationView = findViewById(R.id.FinishView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navHome:
                        Intent home = new Intent(getApplicationContext(),WelcomeScreen.class);
                        startActivity(home);
                        break;
                    case R.id.navSettings:
                        Intent settings = new Intent(getApplicationContext(),Settings.class);
                        startActivity(settings);
                        break;
                    case R.id.navContact:
                        Toast.makeText(getApplicationContext(), "Contact us Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navAbout:
                        Toast.makeText(getApplicationContext(), "About us Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navHelp:
                        Toast.makeText(getApplicationContext(), "Help Selected", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        String percent = roundTwo(percentage);

        String fail = "Sorry, but you failed the test. You got " + right + " questions right and " + wrong + " questions wrong, which is " + percent + "% which doesn't make the cut, better luck next time";
        String pass = "Congratulations you passed the test! You got " + right + " questions right and " + wrong + " questions wrong, which is " + percent + "%! Good job.";

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
