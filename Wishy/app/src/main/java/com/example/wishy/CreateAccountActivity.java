package com.example.wishy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    //Declaring variables
    private EditText userName, name, emailAddress, password, confirmPassword;
    private Button createAccount;
    private FirebaseHandler fHandler;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.create_account);

        //Initializing Firebase Auth and Firebase Handler
        fHandler = new FirebaseHandler();
        mAuth = FirebaseAuth.getInstance();

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

            //Declaring variables and setting to false
            boolean validUsername = false, validEmail = false, validPassword = false;

            //If statements to check if all parameters are valid, if so then allow the button to be clicked
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

    public void createAccount(View view){

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

                    WishlistItem testItem = new WishlistItem(34.99,"Hollister","coat","www.google.ca");
                    WishlistItem testItemTwo = new WishlistItem(40.00,"Asos","Jacket","www.google.ca");

                    List<WishlistItem> testList = new ArrayList<>();
                    testList.add(testItem);
                    testList.add(testItemTwo);

                    HashMap<String, Object> userInfo = new HashMap<>();
                    userInfo.put("name",name.getText().toString().trim());
                    userInfo.put("username",userName.getText().toString().trim());
                    userInfo.put("email",email);
                    userInfo.put("password",userPassword);

                    fHandler.setfUser(mAuth.getCurrentUser());
                    fHandler.addUserInfo(userInfo);

                    for(WishlistItem w : testList){
                        fHandler.addWishlistItem(w);
                    }
                    Intent homeIntent = new Intent(CreateAccountActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }
            }
        });

    }
}
