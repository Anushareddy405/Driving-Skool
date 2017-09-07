package com.example.anusha.project_driving.models;

import java.io.Serializable;

/**
 * Created by Anusha on 31-03-2017.
 */

public class UserModel implements Serializable{
    private String user_id = "";
    private String full_name = "";
    private String mobile_num = "";
    private String email = "";
    private String address = "";
    private String total_fee = "";
    private String paid_fee = "";

    public UserModel()
    {

    }

    public   UserModel(String user_id,String full_name,String mobile_num,String email,String address,String total_fee,String paid_fee){
        this.address=address;
        this.email=email;
        this.user_id=user_id;
        this.total_fee=total_fee;
        this.full_name=full_name;
        this.paid_fee=paid_fee;
        this.mobile_num=mobile_num;
    }

    public String getAddress() {

        return address;
    }

    public String getUser_id() {

        return user_id;
    }

    public void setUser_id(String user_id) {

        this.user_id = user_id;
    }

    public String getFull_name() {

        return full_name;
    }

    public void setFull_name(String full_name) {

        this.full_name = full_name;
    }

    public String getMobile_num() {

        return mobile_num;
    }

    public void setMobile_num(String mobile_num) {

        this.mobile_num = mobile_num;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getTotal_fee() {

        return total_fee;
    }

    public void setTotal_fee(String total_fee) {

        this.total_fee = total_fee;
    }

    public String getPaid_fee() {

        return paid_fee;
    }

    public void setPaid_fee(String paid_fee) {

        this.paid_fee = paid_fee;
    }
}
