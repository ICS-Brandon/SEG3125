package com.example.wishy;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    //Declaring variables
    private EditText userName, name, emailAddress, password, confirmPassword;
    private Button createAccount;
    private FirebaseAuth mAuth;
    private FirebaseFirestore myFstore;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.create_account);

        //Initializing Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        myFstore = FirebaseFirestore.getInstance();

        //initialize create account button and stop it from being clicked
        createAccount = findViewById(R.id.createAccount);
        createAccount.setClickable(false);

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

            //Delcaring variables and setting to false
            boolean validUsername = false, validEmail = false, validPassword = false;

            //If statements to check if all paramaters are valid, if so then allow the button to be clicked
            if(userName.getText().toString().length() > 7)
                validUsername = true;

            if(Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches())
                validEmail = true;

            if(password.getText().toString().equals(confirmPassword.getText().toString()) && password.getText().toString().length() > 6)
                validPassword = true;

            if(validUsername && validEmail && validPassword)
                createAccount.setClickable(true);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void createAccount(){

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

                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("name",name.getText().toString().trim());
                    userInfo.put("username",userName.getText().toString().trim());
                    userInfo.put("email",email);
                    userInfo.put("password",userPassword);

                    myFstore.collection("user_profiles").add(userInfo);
                }
            }
        });
    }
}
