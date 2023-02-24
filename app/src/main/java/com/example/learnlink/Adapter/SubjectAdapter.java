package com.example.learnlink.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnlink.Interface.SubjectRecyclerViewInterface;
import com.example.learnlink.Model.Subject;
import com.example.learnlink.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectViewHolder> {
    private final SubjectRecyclerViewInterface subjectRecyclerViewInterface;
    Context context;
    ArrayList<Subject> subjects;

    public SubjectAdapter(Context context, ArrayList<Subject> subjects , SubjectRecyclerViewInterface subjectRecyclerViewInterface) {
        this.context = context;
        this.subjects = subjects;
        this.subjectRecyclerViewInterface = subjectRecyclerViewInterface;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectViewHolder(LayoutInflater.from(context).inflate(R.layout.item_subject,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.name.setText(subject.getName());
        holder.yearName.setText(subject.getYear().getName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectRecyclerViewInterface.onItemClick(subjects.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

}
