package com.example.wishy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailEdit, passwordEdit;
    private Button login, createAccount;
    private FirebaseAuth mAuth;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        emailEdit = findViewById(R.id.emailEdit);
        emailEdit.addTextChangedListener(watcher);

        passwordEdit = findViewById(R.id.passwordEdit);
        passwordEdit.addTextChangedListener(watcher);

        login = findViewById(R.id.login);
        createAccount = findViewById(R.id.createAccountPortal);

        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void userLogin(View view){

        email = emailEdit.getText().toString().trim();
        password = passwordEdit.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password);

        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);

    }

    public void accountCreation(View view){

        Intent intent = new Intent(MainActivity.this,CreateAccountActivity.class);
        startActivity(intent);

    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            //Declaring variables and setting to false
            boolean validEmail = false, validPassword = false;

            //If statements to check if all parameters are valid, if so then allow the button to be clicked
            if(Patterns.EMAIL_ADDRESS.matcher(emailEdit.getText().toString()).matches())
                validEmail = true;

            if(passwordEdit.getText().toString().length() > 6)
                validPassword = true;

            if(validEmail && validPassword)
                login.setClickable(true);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
