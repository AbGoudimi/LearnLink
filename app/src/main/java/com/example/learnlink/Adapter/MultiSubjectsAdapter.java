package com.example.learnlink.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnlink.Model.Student;
import com.example.learnlink.Model.Subject;
import com.example.learnlink.R;

import java.util.ArrayList;

public class MultiSubjectsAdapter extends RecyclerView.Adapter<MultiSubjectViewHolder>{

    ArrayList<Subject> subjects;
    Student student;
    Context context;
    public MultiSubjectsAdapter(Context context,Student student, ArrayList<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
        this.student=student;

    }

    public ArrayList<Subject> getSelected() {
        ArrayList<Subject> selected = new ArrayList<>();
        for(int i = 0 ; i<student.getTutorSubjects().size();i++){
            selected.add(student.getTutorSubjects().get(i));
        }
        return selected;
    }

    @NonNull
    @Override
    public MultiSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MultiSubjectViewHolder(LayoutInflater.from(context).inflate(R.layout.item_select_subject,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MultiSubjectViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.bind(subject,student);
        holder.name.setText(subject.getName());
        holder.yearName.setText(subject.getYear().getName());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}

