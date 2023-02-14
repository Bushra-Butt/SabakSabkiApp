package com.example.recordstudent;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StudentRVAdapter extends RecyclerView.Adapter<StudentRVAdapter.StudentVH> {

    ArrayList<DailyDiary> DList;
    DbController controller;
    public StudentRVAdapter(ArrayList<DailyDiary> DiaryList) {
        this.DList = DiaryList;
    }
    @NonNull
    @Override
    public StudentRVAdapter.StudentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerecord, parent, false);
        return new StudentRVAdapter.StudentVH(itemView);
    }
    public void addItem(DailyDiary item) {
        DList.add(item);
        notifyDataSetChanged();
//        recyclerView.setLayoutManager(new LinearLayoutManager(StudentRVAdapter.StudentVH));
        new StudentRVAdapter(DList);
    }
    @Override
    public void onBindViewHolder(@NonNull StudentVH holder, int position) {
        if(!DList.isEmpty()) {
            holder.data = DList.get(position);
            holder.name.setText("Student: " + holder.data.getStName());
            holder.manzil.setText("Manzil: " + holder.data.getManzilNo());
            holder.sabak.setText("Sabak: " + holder.data.getSabakNo());
            holder.sabki.setText("Sabki: " + holder.data.getSabkiNo());
            holder.status.setText("Status:" + holder.data.isStatusofManzil());
            holder.date.setText("Date: " + holder.data.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return DList.size();
    }

    public class StudentVH extends RecyclerView.ViewHolder {
        TextView name, manzil, sabak, sabki, status, instname,nameofdtudent,date;
        Button add, addStudent, cancelButton, AddButton;
        DailyDiary data;
        TextView okay_text, cancel_text;
//        RecyclerView.Adapter adapter;
        RecyclerView recyclerView;
        AutoCompleteTextView autoCompleteTextViewSabak, autoCompleteTextViewSabki, autoCompleteTextViewManzil, autoCompleteTextViewStatus;
        public StudentVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date= itemView.findViewById(R.id.date);
            controller=new DbController(itemView.getContext());
//            instname = itemView.findViewById(R.id.instname);
            manzil = itemView.findViewById(R.id.Manzil);
            sabak = itemView.findViewById(R.id.Sabak);
            sabki = itemView.findViewById(R.id.Sabki);
            status = itemView.findViewById(R.id.Status);
            add = itemView.findViewById(R.id.AddToday);
            addStudent = itemView.findViewById(R.id.addStudents);
            Dialog dialog = new Dialog(itemView.getContext());
            add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    dialog.setContentView(R.layout.customdialog);
                    String[] arr = {"","","1","2","3","4","5","6","7","8","9","10",
                            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
                    String[] arr1 = {"Learn", "Not Learn"};
                    autoCompleteTextViewManzil = (AutoCompleteTextView) dialog.findViewById(R.id.ManzilAuto);
                    autoCompleteTextViewSabak = (AutoCompleteTextView) dialog.findViewById(R.id.autoCompleteTextViewSabak);
                    autoCompleteTextViewSabki = (AutoCompleteTextView) dialog.findViewById(R.id.autoCompleteTextViewSabki);
                    autoCompleteTextViewStatus = (AutoCompleteTextView) dialog.findViewById(R.id.statusAuto);
                    nameofdtudent = (TextView) dialog.findViewById(R.id.editTextTextPersonName);
                    nameofdtudent.setText(name.getText().toString());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, arr);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, arr1);
//                    autoCompleteTextViewManzil.setThreshold(1);
                    autoCompleteTextViewManzil.setAdapter(adapter);
//                    autoCompleteTextViewSabak.setThreshold(1);
                    autoCompleteTextViewSabak.setAdapter(adapter);
//                    autoCompleteTextViewSabki.setThreshold(1);
                    autoCompleteTextViewSabki.setAdapter(adapter);
//                    autoCompleteTextViewStatus.setThreshold(1);
                    autoCompleteTextViewStatus.setAdapter(adapter1);
                    autoCompleteTextViewManzil.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            autoCompleteTextViewManzil.showDropDown();
                        }
                    });
                    autoCompleteTextViewStatus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            autoCompleteTextViewStatus.showDropDown();
                        }
                    });
                    autoCompleteTextViewSabak.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            autoCompleteTextViewSabak.showDropDown();
                        }
                    });
                    autoCompleteTextViewSabki.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            autoCompleteTextViewSabki.showDropDown();
                        }
                    });
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setCancelable(false);
//                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                    okay_text = dialog.findViewById(R.id.okay_text);
                    cancel_text = dialog.findViewById(R.id.cancel_text);
                    okay_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            Toast.makeText(view.getContext(), "Added", Toast.LENGTH_SHORT).show();
                            DailyDiary dailyDiary=new DailyDiary();
                            dailyDiary.setDate(date);
                            dailyDiary.setSabakNo(Integer.valueOf(autoCompleteTextViewSabak.getText().toString()));
                            dailyDiary.setManzilNo(Integer.valueOf(autoCompleteTextViewManzil.getText().toString()));
                            dailyDiary.setStatusofManzil(autoCompleteTextViewStatus.getText().toString());
                            String[] name=nameofdtudent.getText().toString().split(":");
                            dailyDiary.setStName(name[1]);
                            controller.AddStudentintoDiary(dailyDiary);
                            addItem(dailyDiary);
//                            adapter.notifyDataSetChanged();

                            Toast.makeText(view.getContext(), "okay clicked", Toast.LENGTH_SHORT).show();
//                            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//                            adapter=new StudentRVAdapter(DateList);
//                            recyclerView.setAdapter(adapter);
                        }
                    });
                    cancel_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Toast.makeText(view.getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }
}