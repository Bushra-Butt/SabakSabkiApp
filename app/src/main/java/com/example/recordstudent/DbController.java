package com.example.recordstudent;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DbController {
    DBHelper db;
    int id;
    public DbController(Context con)
    {
        this.db=new DBHelper(con);
    }
//    public boolean getVerification(String name,String pass)
//    {
//        if(db.verifyUser(name,pass))
//        {
//            id=db.GetUserAgainstuserName(name).userId;
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//    public List<String> getDatesAgaintId()
//    {
//        return db.GetAllDates(id);
//    }
//    public List<DailyDiary> getAllStudentsAgainstDate(String date)
//    {
//        return db.GetresultAgainstUserAndDate(id,date);
//    }
//    public boolean isUserUnique(String username)
//    {
//        if(db.GetUserAgainstuserName(username)!=null) {
//            return true;
//        }
//        return false;
//    }
//    public boolean AddUser(User user) {
//        if (user != null) {
//            db.AddUser(user);
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
    public ArrayList<DailyDiary> getAllStudents()
    {
         return db.Getresult();
    }
    public ArrayList<DailyDiary> getAllPrevious()
    {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String yesterdayAsString = dateFormat.format(cal.getTime());
        return db.GetPreviousDayRecord(yesterdayAsString);
    }
    public void AddStudentintoDiary(String date, int sabkiNo, int manzilNo, int sabakNo,String manzil,String name)
    {
        DailyDiary d=new DailyDiary(date,sabkiNo,manzilNo,sabakNo,manzil,name);
        if(d!=null) {
            db.AddStudentRecord(d);
        }
    }
    public void AddStudentintoDiary(DailyDiary dt)
    {
        if(dt!=null) {
            db.AddStudentRecord(dt);
        }
    }
    public List<DailyDiary> searchByName(String name)
    {
        return db.GetAgainstuserName(name);
    }

}
