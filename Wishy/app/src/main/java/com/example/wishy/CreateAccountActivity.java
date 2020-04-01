package com.example.wishy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.hatamiarash.toast.RTLToast;

public class CreateAccountActivity extends AppCompatActivity {

    //Declaring variables
    private EditText userName, name, emailAddress, password, confirmPassword;
    private Button createAccount;
    private FirebaseHandler fHandler;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.create_account);

        //Initializing Firebase Auth and Firebase Handler
        fHandler = new FirebaseHandler();
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //initialize create account button and stop it from being clicked
        createAccount = findViewById(R.id.createAccount);

        //Initializing EditText fields and assigning a text watcher
        userName = findViewById(R.id.usernameEditAccount);
        userName.addTextChangedListener(watcher);

        name = findViewById(R.id.usernameEditAccount);
        name.addTextChangedListener(watcher);

        emailAddress = findViewById(R.id.emailEditCreate);
        emailAddress.addTextChangedListener(watcher);

        password = findViewById(R.id.passwordEditCreate);
        password.addTextChangedListener(watcher);

        confirmPassword = findViewById(R.id.passwordConfirmCreate);
        confirmPassword.addTextChangedListener(watcher);

    }

    //Text watcher for EditText fields for account creation
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void createAccount(View view){

        boolean createValid = checkValid();

        if(createValid){
            //Getting String references of values in EditText fields for email and password
            final String email = emailAddress.getText().toString().trim();
            final String userPassword = password.getText().toString().trim();

            //Creating user on Auth server with given email and password
            //TODO add check to see if user already exists with that email
            mAuth.createUserWithEmailAndPassword(email,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    //Checks if user was added to auth server, if so user is added to database with additional information
                    if(task.isSuccessful()){
                        //addInformation(email,userPassword);
                        Intent homeIntent = new Intent(CreateAccountActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                    }
                    else{
                        RTLToast.error(getApplicationContext(),"Email is already in use, please try a different email",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public boolean checkValid(){

        RTLToast.Config.getInstance().setTextSize(10).apply();

        //Declaring variables and setting to false
        boolean validUsername = false, validEmail = false, validPassword = false;

        //If statements to check if all parameters are valid, if so then allow the button to be clicked
        if(userName.getText().toString().length() > 3)
            validUsername = true;
        else if(getCurrentFocus().getId() == userName.getId())
            RTLToast.warning(getApplicationContext(),"Username is too short, please have a username longer than 3 characters",Toast.LENGTH_SHORT).show();

        if(Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches())
            validEmail = true;
        else if(getCurrentFocus().getId() == emailAddress.getId())
            RTLToast.warning(getApplicationContext(),"Not a valid email address",Toast.LENGTH_SHORT).show();

        if(password.getText().toString().equals(confirmPassword.getText().toString()) && password.getText().toString().length() > 6)
            validPassword = true;
        else if(getCurrentFocus().getId() == password.getId() || getCurrentFocus().getId() == confirmPassword.getId())
            RTLToast.warning(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();

        if(validUsername && validEmail && validPassword)
            return true;
        else
            return false;
    }
}
