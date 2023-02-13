package com.example.recordstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class recentRecord extends AppCompatActivity {

    DbController controller;
    ArrayList<DailyDiary> DateList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_record);
        controller=new DbController(recentRecord.this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewS);
        recyclerView.setHasFixedSize(true);
        DateList.addAll(controller.getAllPrevious());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new StudentRVAdapter(DateList);
        recyclerView.setAdapter(adapter);
    }
}