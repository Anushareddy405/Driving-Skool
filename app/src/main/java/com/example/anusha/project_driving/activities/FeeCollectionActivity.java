package com.example.anusha.project_driving.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anusha.project_driving.R;
import com.example.anusha.project_driving.database.DBHelper;

/**
 * Created by Anusha on 06-04-2017.
 */

public class FeeCollectionActivity extends AppCompatActivity {
    TextView paidfee, totalfee, duefee;
    String paidFee, totalFee, dueFee, remFee, remamount, mobileNumber;
    EditText remfee;
    Button btn;
    DBHelper dbHelper;
    int remAmount;
    long id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feecollection);

        totalfee = (TextView) findViewById(R.id.total_fee);
        paidfee = (TextView) findViewById(R.id.paid_fee);
        duefee = (TextView) findViewById(R.id.due_fee);
        remfee = (EditText) findViewById(R.id.rem_fee);
        btn = (Button) findViewById(R.id.btn);
        dbHelper = new DBHelper(FeeCollectionActivity.this);

        Bundle b = getIntent().getExtras();
        totalFee = b.getString("TotalFee");
        paidFee = b.getString("PaidFee");
        dueFee = b.getString("DueFee");
        mobileNumber = b.getString("MobileNumber");

        totalfee.setText(totalFee);
        paidfee.setText(paidFee);
        duefee.setText(dueFee);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remFee = remfee.getText().toString();
                remAmount = Integer.parseInt(remFee) + Integer.parseInt(paidFee);
                remamount = remAmount + "";
                if(Integer.parseInt(remFee) <= Integer.parseInt(dueFee)) {
                    id = dbHelper.updateFee(mobileNumber, remamount);
                }
                else{
                    remfee.setError("amount is higher than due fee");
                }
                if(id>0){
                    Toast.makeText(FeeCollectionActivity.this,"updated",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

        });

    }
}
