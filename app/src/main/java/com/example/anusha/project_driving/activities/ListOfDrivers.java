package com.example.anusha.project_driving.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anusha.project_driving.R;
import com.example.anusha.project_driving.database.DBHelper;
import com.example.anusha.project_driving.models.UserModel;

import java.util.ArrayList;

/**
 * Created by Anusha on 03-04-2017.
 */

public class ListOfDrivers extends AppCompatActivity {
    ListView mListView;
    DBHelper mDbHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_list);
        mListView = (ListView) findViewById(R.id.listView);

        mDbHelper = new DBHelper(ListOfDrivers.this);
        final ArrayList<String> mDriversList = mDbHelper.getMovieList();
        Log.e("Driver data", mDriversList.get(0));
        //adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListOfDrivers.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mDriversList);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ListOfDrivers.this, mDriversList.get(position).toString(), Toast.LENGTH_SHORT).show();
                UserModel mUserModel = mDbHelper.getUserDetails(mDriversList.get(position).toString());
                Intent mIntent = new Intent(ListOfDrivers.this,UserDetailsActivity.class);
                mIntent.putExtra("UserModel",mUserModel);
                startActivity(mIntent);
            }
        });
    }
}
