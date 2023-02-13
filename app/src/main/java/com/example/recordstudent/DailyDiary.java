package com.example.recordstudent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DailyDiary {
    int DiaryId;
    String Date;
    int SabkiNo;
    int ManzilNo;
    int SabakNo;
    String StatusofManzil;
    String stName;
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }


    public int getDiaryId() {
        return DiaryId;
    }
    public String isStatusofManzil() {
        return StatusofManzil;
    }

    public void setStatusofManzil(String statusofManzil) {
        StatusofManzil = statusofManzil;
    }
    public DailyDiary(String date, int sabkiNo, int manzilNo, int sabakNo,String manzil,String name) {
        Date = date;
        SabkiNo = sabkiNo;
        ManzilNo = manzilNo;
        SabakNo = sabakNo;
        this.StatusofManzil=manzil;
        this.stName=name;
    }
    public DailyDiary() {

    }
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getSabkiNo() {
        return SabkiNo;
    }

    public void setSabkiNo(int sabkiNo) {
        SabkiNo = sabkiNo;
    }

    public int getManzilNo() {
        return ManzilNo;
    }

    public void setManzilNo(int manzilNo) {
        ManzilNo = manzilNo;
    }

    public int getSabakNo() {
        return SabakNo;
    }

    public void setSabakNo(int sabakNo) {
        SabakNo = sabakNo;
    }

}
