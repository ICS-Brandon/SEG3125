package com.example.lab1questionnaire;

import android.content.Context;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.robertlevonyan.views.expandable.Expandable;

import org.w3c.dom.Text;

public class CreateQuiz extends AppCompatActivity {

    LinearLayout scrollLayout;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.create_quiz);

        scrollLayout = findViewById(R.id.ScrollLayout);
    }
}
