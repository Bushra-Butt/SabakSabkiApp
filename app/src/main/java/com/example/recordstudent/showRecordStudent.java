package com.example.recordstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class showRecordStudent extends AppCompatActivity {
    DbController controller;
    ArrayList<DailyDiary> DateList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    EditText nameOfStudent;
    Button addButton,cancelButton,addStudent,addNew,searchByname;
    TextView name,sabak,sabki,manzil,learing,cancel,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record_student);
        controller=new DbController(showRecordStudent.this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewS);
        recyclerView.setHasFixedSize(true);
        addStudent=findViewById(R.id.addStudents);
        searchByname=findViewById(R.id.searchByName);
        DateList.addAll(controller.getAllStudents());
        Dialog dialog1=new Dialog(this);
        searchByname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.setContentView(R.layout.searchbyname);
                dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog1.setCancelable(false);
                cancel= dialog1.findViewById(R.id.cancel);
                search =  dialog1.findViewById(R.id.search);
                nameOfStudent=  (EditText) dialog1.findViewById(R.id.nameofstudent);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                        Toast.makeText(view.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                        Toast.makeText(view.getContext(), "Successfull", Toast.LENGTH_SHORT).show();
                        DateList = new ArrayList<>();
                        DateList.addAll(controller.searchByName(nameOfStudent.getText().toString()));
                        adapter.notifyDataSetChanged();
//                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        adapter=new StudentRVAdapter(DateList);
                        recyclerView.setAdapter(adapter);
                    }
                });
                dialog1.show();

            }
        });
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.setContentView(R.layout.addstudentdialog);
                dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog1.setCancelable(false);
                cancelButton= (Button) dialog1.findViewById(R.id.cancelbutton2);
                addButton =  (Button) dialog1.findViewById(R.id.addbutton);
                name=  (TextView) dialog1.findViewById(R.id.Name);
                sabak=  (TextView) dialog1.findViewById(R.id.SabakNumber);
                sabki=  (TextView) dialog1.findViewById(R.id.SabkiNumber);
                manzil=  (TextView) dialog1.findViewById(R.id.manzilno);
                learing=  (TextView) dialog1.findViewById(R.id.Statusofmanzil);
                DateList.addAll(controller.getAllStudents());
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                        Toast.makeText(view.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                        controller.AddStudentintoDiary(date,Integer.valueOf(sabki.getText().toString()),Integer.valueOf(manzil.getText().toString()),Integer.valueOf(sabak.getText().toString()),learing.getText().toString(),name.getText().toString());
                        Toast.makeText(view.getContext(), "Added", Toast.LENGTH_SHORT).show();
                        DateList.addAll(controller.getAllStudents());
                        adapter.notifyDataSetChanged();
                        adapter=new StudentRVAdapter(DateList);
                        recyclerView.setAdapter(adapter);
                    }
                });
                dialog1.show();

            }
        });
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new StudentRVAdapter(DateList);
        recyclerView.setAdapter(adapter);
    }
}