package com.example.wishy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.settings_layout);
    }

    public void homeFromSettings(View view){
        Intent returnIntent = new Intent(SettingsActivity.this,HomeActivity.class);
        startActivity(returnIntent);
    }

    public void addFromSettings(View view){
        Intent returnIntent = new Intent(SettingsActivity.this,AddEditActivity.class);
        startActivity(returnIntent);
    }
    public void helpFromSettings(View view){
        Intent returnIntent = new Intent(SettingsActivity.this,HelpActivity.class);
    }
}
