package com.example.anusha.project_driving.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anusha.project_driving.R;
import com.example.anusha.project_driving.utils.SharedPrefer;

/**
 * Created by Anusha on 30-03-2017.
 */

public class LoginActivity extends AppCompatActivity {
    EditText userName, passWord;
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userName = (EditText) findViewById(R.id.usename_editText);
        passWord = (EditText) findViewById(R.id.password_editText);
        login = (Button) findViewById(R.id.login_button);


        final String username = "admin";
        final String password = "password";
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().equals(username) && passWord.getText().toString().equals(password)) {
                    SharedPrefer msharedPrefer = new SharedPrefer(LoginActivity.this);

                    msharedPrefer.setUsername(username);
                    msharedPrefer.setPassword(password);
                    Intent mIntent = new Intent(LoginActivity.this, DashBoardActivity.class);
                    startActivity(mIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "user name or password are wrong", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
