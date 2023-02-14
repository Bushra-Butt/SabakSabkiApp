package com.example.recordstudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "Record.db",null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSTatement = "CREATE TABLE Diary (ID Integer PRIMARY KEY AUTOINCREMENT," +
                "Date Text," +
                "SabkiNo Integer," +
                "ManzilNo Integer," +
                "SabakNo Integer," +
                "Status boolean,"+
                "StudentName Text);";
        sqLiteDatabase.execSQL(createTableSTatement);

    }
//    public void AddUser(User user)
//    {
//        SQLiteDatabase MyConn = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("password",user.getPassword());
//        cv.put("username",user.getName());
//        MyConn.insert("User",null,cv);
//        MyConn.close();
//    }
//    public User GetUserAgainstId(int id)
//    {
//        SQLiteDatabase MyConn = this.getReadableDatabase();
//        String sql = "SELECT * FROM User WHERE userId="+"id"+";";
//        Cursor cursor = MyConn.rawQuery(sql,null);
//        int d = cursor.getInt(0);
//        String name = cursor.getString(2);
//        String pass=cursor.getString(1);
//        User user = new User(name,pass);
//        cursor.close();
//        MyConn.close();
//        return user;
//    }
//    public boolean verifyUser(String Name, String pass)
//    {
//        SQLiteDatabase MyConn = this.getReadableDatabase();
//        String sql = "SELECT * FROM User WHERE username="+"Name"+ " "+"AND password="+pass+";";
//        Cursor cursor = MyConn.rawQuery(sql,null);
//        if(cursor.getCount()>0) {
//            cursor.close();
//            MyConn.close();
//            return true;
//        }
//        else {
//            cursor.close();
//            MyConn.close();
//            return false;
//        }
//
//    }
    public ArrayList<DailyDiary> GetAgainstuserName(String StName)
    {
        SQLiteDatabase MyConn = this.getReadableDatabase();
        String sql = "SELECT * FROM Diary WHERE StudentName='"+StName+"';";
        Cursor cursor = MyConn.rawQuery(sql,null);
        ArrayList<DailyDiary> dd= new ArrayList<>();;
        if(cursor.moveToFirst())
        {
            do {
                int aid = cursor.getInt(0);
                String date= cursor.getString(1);
                int sabki = cursor.getInt(2);
                int manzil = cursor.getInt(3);
                int sabk = cursor.getInt(4);
                String sta = cursor.getString(5);
                String name=cursor.getString(6);
                dd.add(new DailyDiary(date,sabki,manzil,sabk,sta,name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        MyConn.close();
        return dd;
    }
    public void AddStudentRecord(DailyDiary rt) {
        SQLiteDatabase MyConn = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Date" , rt.getDate());
        cv.put("SabkiNo" , rt.getSabkiNo());
        cv.put("SabakNo" , rt.getSabakNo());
        cv.put("Status" , rt.isStatusofManzil());
        cv.put("ManzilNo" , rt.getManzilNo());
        cv.put("StudentName",rt.getStName());
        MyConn.insert("Diary",null,cv);
        MyConn.close();
    }
    public ArrayList<DailyDiary> Getresult()
    {
        SQLiteDatabase MyConn = this.getReadableDatabase();
        String sql = "SELECT * FROM Diary order by Date DESC";
        Cursor cursor = MyConn.rawQuery(sql,null);
        ArrayList<DailyDiary> resultList = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                int aid = cursor.getInt(0);
                String date= cursor.getString(1);
                int sabki = cursor.getInt(2);
                int manzil = cursor.getInt(3);
                int sabk = cursor.getInt(4);
                String sta = cursor.getString(5);
                String name=cursor.getString(6);
                resultList.add(new DailyDiary(date,sabki,manzil,sabk,sta,name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        MyConn.close();
        return resultList;
    }
//    public ArrayList<DailyDiary> GetresultAgainstUser(int uid)
//    {
//        SQLiteDatabase MyConn = this.getReadableDatabase();
//        String sql = "SELECT * FROM Diary WHERE userId="+uid+";";
//        Cursor cursor = MyConn.rawQuery(sql,null);
//        ArrayList<DailyDiary> resultList = new ArrayList<>();
//        if(cursor.moveToFirst()) {
//            do {
//                int aid = cursor.getInt(0);
//                String date= cursor.getString(1);
//                int sabki = cursor.getInt(2);
//                int manzil = cursor.getInt(3);
//                int sabk = cursor.getInt(4);
//                String sta = cursor.getString(3);
//                int userid = cursor.getInt(7);
//                String name=cursor.getString(6);
//                resultList.add(new DailyDiary(date,sabki,manzil,sabk,userid,sta,name));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        MyConn.close();
//        return resultList;
//    }
    public ArrayList<DailyDiary> GetPreviousDayRecord(String date)
    {

        SQLiteDatabase MyConn = this.getReadableDatabase();
        String sql = "SELECT * FROM Diary WHERE Date="+date+" ORDER BY Date DESC;";
        Cursor cursor = MyConn.rawQuery(sql,null);
        ArrayList<DailyDiary> resultList = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                int aid = cursor.getInt(0);
                String adate= cursor.getString(1);
                int sabki = cursor.getInt(2);
                int manzil = cursor.getInt(3);
                int sabk = cursor.getInt(4);
                String sta = cursor.getString(5);
                String name=cursor.getString(6);
                resultList.add(new DailyDiary(adate,sabki,manzil,sabk,sta,name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        MyConn.close();
        return resultList;
    }
//    public ArrayList<String> GetAllDates(int uid)
//    {
//        SQLiteDatabase MyConn = this.getReadableDatabase();
//        String sql = "SELECT * FROM Diary WHERE userId="+uid+";";
//        Cursor cursor = MyConn.rawQuery(sql,null);
//        ArrayList<String> resultList = new ArrayList<>();
//        if(cursor.moveToFirst()) {
//            do {
//                String date= cursor.getString(1);
//                resultList.add(date);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        MyConn.close();
//        return resultList;
//    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Diary");
        onCreate(sqLiteDatabase);
    }
}



