package com.example.learnlink;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectViewHolder> {

    Context context;
    List<Subject> subjects;

    public SubjectAdapter(Context context, List<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectViewHolder(LayoutInflater.from(context).inflate(R.layout.subject_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {

        holder.name.setText(subjects.get(position).getName());
        holder.yearName.setText(subjects.get(position).getYear().getName());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}
