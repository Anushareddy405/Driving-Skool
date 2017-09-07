package com.example.anusha.project_driving.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.anusha.project_driving.R;
import com.example.anusha.project_driving.database.DBHelper;
import com.example.anusha.project_driving.models.UserModel;

/**
 * Created by Anusha on 30-03-2017.
 */

public class UserDetailsActivity extends AppCompatActivity {
    TextView fullName, mobileNumber, eMail, addres, totalFee, paidFee;
    UserModel uModel;
    Button collectFee, submit;
    String paidfee, totalfee, fullname, mobilenum, email, address, remainfee;
    LinearLayout checkedTl;
    String number = "";
    DBHelper mDBDbHelper;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        mDBDbHelper = new DBHelper(UserDetailsActivity.this);

        fullName = (TextView) findViewById(R.id.full_name);
        mobileNumber = (TextView) findViewById(R.id.mobile_num);
        eMail = (TextView) findViewById(R.id.email);
        addres = (TextView) findViewById(R.id.address);
        totalFee = (TextView) findViewById(R.id.total_fee);
        paidFee = (TextView) findViewById(R.id.paid_fee);
        collectFee = (Button) findViewById(R.id.collect_fee);
        submit = (Button) findViewById(R.id.submit);
        checkedTl = (LinearLayout) findViewById(R.id.checked_tl);

        Bundle b = getIntent().getExtras();
        uModel = (UserModel) b.getSerializable("UserModel");

        fullname = uModel.getFull_name().toString();
        mobilenum = uModel.getMobile_num().toString();
        email = uModel.getEmail().toString();
        address = uModel.getAddress().toString();
        totalfee = uModel.getTotal_fee().toString();
        paidfee = uModel.getPaid_fee().toString();

        fullName.setText(fullname);
        mobileNumber.setText(mobilenum);
        eMail.setText(email);
        addres.setText(address);
        totalFee.setText(totalfee);
        paidFee.setText(paidfee);

        if (totalfee.equals(paidfee)) {
            collectFee.setVisibility(View.GONE);
        }

        String doneClasses = mDBDbHelper.getClassesDetails(mobilenum);
        String[] arrayStr = doneClasses.split(",");
        int leng = checkedTl.getChildCount();
        for (int i = 0; i < leng; i++) {
            View tableRow = checkedTl.getChildAt(i);
            if (tableRow instanceof TableRow) {
                int len = ((TableRow) tableRow).getChildCount();
                for (int j = 0; j < len && arrayStr.length - 1 > count; j++) {
                    View checked = ((TableRow) tableRow).getChildAt(j);
                    if (checked instanceof CheckBox) {
                        ((CheckBox) checked).setChecked(true);
                        count++;
                    }
                }
            }
        }

        int remainingfee = Integer.parseInt(totalfee) - Integer.parseInt(paidfee);
        remainfee = remainingfee + "";
        collectFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(UserDetailsActivity.this, FeeCollectionActivity.class);
                mIntent.putExtra("TotalFee", totalfee);
                mIntent.putExtra("PaidFee", paidfee);
                mIntent.putExtra("DueFee", remainfee);
                mIntent.putExtra("MobileNumber", mobilenum);
                startActivity(mIntent);
                finish();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int leng = checkedTl.getChildCount();
                for (int i = 0; i < leng; i++) {
                    View tableRow = checkedTl.getChildAt(i);
                    if (tableRow instanceof TableRow) {
                        int len = ((TableRow) tableRow).getChildCount();
                        for (int j = 0; j < len; j++) {
                            View checked = ((TableRow) tableRow).getChildAt(j);
                            if (checked instanceof CheckBox) {
                                if (((CheckBox) checked).isChecked()) {
                                    number = number + "," + ((CheckBox) checked).getText().toString();
                                }
                            }
                        }
                    }
                }

                if (!mDBDbHelper.isClassesPresent(mobilenum)) {
                    long id = mDBDbHelper.insertClasses(mobilenum, number);
                    if (id > 0) {
                        finish();
                    }
                } else {
                    long id = mDBDbHelper.updateClasses(mobilenum, number);
                    if (id > 0) {
                        finish();
                    }
                }
            }
        });
    }
}
