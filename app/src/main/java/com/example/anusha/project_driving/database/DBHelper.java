package com.example.anusha.project_driving.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.anusha.project_driving.models.UserModel;

import java.util.ArrayList;

/**
 * Created by Anusha on 31-03-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static final String DB_Name = "driving";
    public static final int DB_VERSION = 1;
    public static final String REGISTRATION_TABLE = "registration_table";
    public static final String USER_ID = "user_id";
    public static final String FULL_NAME = "full_name";
    public static final String MOBILE_NUM = "mobile_number";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String TOTAL_FEE = "total_fee";
    public static final String PAID_FEE = "paid_fee";


    public static final String CLASS_TABLE = "class_table";
    public static final String CLASS_DONE = "class_done";
    public static final String CLASSES_ID = "class_id";


    private static final String REGISTRATION_QUERY = "CREATE TABLE " + REGISTRATION_TABLE + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + FULL_NAME + " VARCHAR NOT NULL, "
            + MOBILE_NUM + " TEXT NOT NULL, "
            + EMAIL + " TEXT NOT NULL, "
            + ADDRESS + " TEXT NOT NULL, "
            + TOTAL_FEE + " TEXT NOT NULL, "
            + PAID_FEE + " TEXT NOT NULL);";

    private final String CLASSES_TABLES = "CREATE TABLE " + CLASS_TABLE + "("
            + CLASSES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + MOBILE_NUM + " VARCHAR NOT NULL, "
            + CLASS_DONE + " TEXT NOT NULL);";


    public DBHelper(Context context) {
        super(context, DB_Name, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(REGISTRATION_QUERY);
        db.execSQL(CLASSES_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long insertTable(UserModel mUserModel) {
        long id = 0;
        db = getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(FULL_NAME, mUserModel.getFull_name());
            cv.put(MOBILE_NUM, mUserModel.getMobile_num());
            cv.put(EMAIL, mUserModel.getEmail());
            cv.put(ADDRESS, mUserModel.getAddress());
            cv.put(TOTAL_FEE, mUserModel.getTotal_fee());
            cv.put(PAID_FEE, mUserModel.getPaid_fee());

            id = db.insert(REGISTRATION_TABLE, null, cv);
        } catch (Exception e) {

        } finally {
            if (db != null)
                db.close();
        }

        return id;


    }

    public ArrayList<String> getMovieList() {
        ArrayList<String> mArray = new ArrayList<>();
        db = getWritableDatabase();
        String query = "Select " + MOBILE_NUM + " from " + REGISTRATION_TABLE;
        Cursor mCursor = db.rawQuery(query, null);
        try {
            if (mCursor.moveToFirst()) {
                do {
                    mArray.add(mCursor.getString(mCursor.getColumnIndex(MOBILE_NUM)));
                } while (mCursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
            if (mCursor != null)
                mCursor.close();
        }
        return mArray;
    }

    public UserModel getUserDetails(String mobileNumber) {
        UserModel userModel = new UserModel();
        db = getWritableDatabase();
        Cursor cursor = null;
        try {
            String Query = "Select * from " + REGISTRATION_TABLE + " where " + MOBILE_NUM + " = ?";
            cursor = db.rawQuery(Query, new String[]{mobileNumber});
            if (cursor.moveToFirst()) {
                userModel.setFull_name(cursor.getString(cursor.getColumnIndex(FULL_NAME)));
                userModel.setMobile_num(cursor.getString(cursor.getColumnIndex(MOBILE_NUM)));
                userModel.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                userModel.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                userModel.setTotal_fee(cursor.getString(cursor.getColumnIndex(TOTAL_FEE)));
                userModel.setPaid_fee(cursor.getString(cursor.getColumnIndex(PAID_FEE)));
            }
            cursor.close();
        } catch (Exception e) {
            if (db != null)
                db.close();
            if (cursor != null)
                cursor.close();
        } finally {
            if (db != null)
                db.close();
            if (cursor != null)
                cursor.close();
        }
        return userModel;
    }

    public long updateFee(String MobileNumber, String PaidFee) {
        long id = 0;
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PAID_FEE, PaidFee);
        id = db.update(REGISTRATION_TABLE, cv, MOBILE_NUM + " = " + MobileNumber, null);
        return id;
    }


    public long insertClasses(String mobileNo, String classesDone) {
        long id = 0;
        db = getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(MOBILE_NUM, mobileNo);
            cv.put(CLASS_DONE, classesDone);

            id = db.insert(CLASS_TABLE, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null)
                db.close();
        }
        return id;
    }

    public String getClassesDetails(String mobileNumber) {
        db = getWritableDatabase();
        String classDone = "";
        Cursor cursor = null;
        try {
            String Query = "Select " + CLASS_DONE + " from " + CLASS_TABLE + " where " + MOBILE_NUM + " = ?";
            cursor = db.rawQuery(Query, new String[]{mobileNumber});
            if (cursor.moveToFirst()) {
                classDone = cursor.getString(cursor.getColumnIndex(CLASS_DONE));
            }
            cursor.close();
        } catch (Exception e) {
            if (db != null)
                db.close();
            if (cursor != null)
                cursor.close();
        } finally {
            if (db != null)
                db.close();
            if (cursor != null)
                cursor.close();
        }
        return classDone;
    }


    public long updateClasses(String MobileNumber, String classesDone) {
        long id = 0;
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CLASS_DONE, classesDone);
        id = db.update(CLASS_TABLE, cv, MOBILE_NUM + " = " + MobileNumber, null);
        return id;
    }


    public boolean isClassesPresent(String mobileNumber) {
        db = getWritableDatabase();
        Cursor cursor = null;
        try {
            String Query = "Select " + CLASS_DONE + " from " + CLASS_TABLE + " where " + MOBILE_NUM + " = ?";
            cursor = db.rawQuery(Query, new String[]{mobileNumber});
            if (cursor.getCount() > 0) {
                db.close();
                return true;
            }

        } catch (Exception e) {
            if (db != null)
                db.close();
            if (cursor != null)
                cursor.close();
        } finally {
            if (db != null)
                db.close();
            if (cursor != null)
                cursor.close();
        }
        return false;
    }
}