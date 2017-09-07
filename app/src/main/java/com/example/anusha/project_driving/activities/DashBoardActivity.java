package com.example.anusha.project_driving.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anusha.project_driving.R;

/**
 * Created by Anusha on 30-03-2017.
 */

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn1,btn2;
    String input;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btn1 = (Button) findViewById(R.id.register_button);
        btn2 = (Button) findViewById(R.id.userdetails_button);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.register_button:
                Intent mIntent = new Intent(DashBoardActivity.this, RegisterActivity.class);
                startActivity(mIntent);
                break;
            case R.id.userdetails_button:
                Intent mIntent2 = new Intent(DashBoardActivity.this, ListOfDrivers.class);
                startActivity(mIntent2);
                break;
        }
    }
}
