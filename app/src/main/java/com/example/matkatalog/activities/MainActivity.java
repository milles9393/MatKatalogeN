package com.example.matkatalog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matkatalog.R;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        addListener();

    }

    private void addListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAttempt(passwordField.getText().toString());
            }
        });
    }

    private void bindViews() {
        loginButton = findViewById(R.id.loginButton);
        passwordField = findViewById(R.id.passwordField);
    }

    private void loginAttempt (String password){
        if(password.equals("hej")){
            loginSucessful();

        } else{
            Toast toast = Toast.makeText(getApplicationContext(), R.string.login_wrong_password, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void loginSucessful(){
        Intent intent = new Intent(getApplicationContext(), OwnedActivity.class);
        startActivity(intent);
    }

}
