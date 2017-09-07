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
import com.example.anusha.project_driving.database.DBHelper;
import com.example.anusha.project_driving.models.UserModel;


public class RegisterActivity extends AppCompatActivity {

    EditText  full_name, mobile_no, email, address, total_fee, paid_fee;
    Button btnReg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        full_name = (EditText) findViewById(R.id.fullname_editText);
        mobile_no = (EditText) findViewById(R.id.mobile_number_editText);
        email = (EditText) findViewById(R.id.email_editText);
        address = (EditText) findViewById(R.id.address_editText);
        total_fee = (EditText) findViewById(R.id.total_fee_editText);
        paid_fee = (EditText) findViewById(R.id.paid_fee_editText);
        btnReg = (Button) findViewById(R.id.reg_button);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = full_name.getText().toString();
                String mobileNo = mobile_no.getText().toString();
                String eMail = email.getText().toString();
                String adress = address.getText().toString();
                String totalFee = total_fee.getText().toString();
                String paidFee = paid_fee.getText().toString();



                if(fullName.length()== 0) {
                    Toast.makeText(RegisterActivity.this, "name cannotbe blank", Toast.LENGTH_LONG).show();
                    full_name.setError("name cannot be blank");
                }
                else if(mobileNo.length()<10){
                    Toast.makeText(RegisterActivity.this, "mobilenum must be 10digits", Toast.LENGTH_LONG).show();
                    mobile_no.setError("name cannot be blank");
                }
                else if(eMail.equals("")){
                    Toast.makeText(RegisterActivity.this, "invalid email", Toast.LENGTH_LONG).show();
                    email.setError("name cannot be blank");
                }
                else if(adress.length()==0){
                    Toast.makeText(RegisterActivity.this, "address must be filled", Toast.LENGTH_LONG).show();
                    address.setError("address cannot be blank");
                }

                else if(Integer.parseInt(paidFee)==0){
                    Toast.makeText(RegisterActivity.this, "please fill correctly", Toast.LENGTH_LONG).show();
                    paid_fee.setError("fee cannot be blank");
                }
                else{
                    UserModel um = new UserModel();
                    um.setFull_name(fullName);
                    um.setMobile_num(mobileNo);
                    um.setEmail(eMail);
                    um.setAddress(adress);
                    um.setTotal_fee(totalFee);
                    um.setPaid_fee(paidFee);

                    DBHelper rd = new DBHelper(RegisterActivity.this);
                    long id = rd.insertTable(um);
                    if (id > 0) {
                        Toast.makeText(RegisterActivity.this, "data inserted  " + id, Toast.LENGTH_SHORT).show();
                        Intent mIntent = new Intent(RegisterActivity.this,DashBoardActivity.class);
                        startActivity(mIntent);
                        finish();
                    }
                }
            }
        });
    }
}
