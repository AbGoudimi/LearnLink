package com.example.learnlink.Adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnlink.R;

public class SubjectViewHolder extends RecyclerView.ViewHolder {
    TextView name,yearName;
    public RelativeLayout relativeLayout ;
    public SubjectViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.subjectName);
        yearName = itemView.findViewById(R.id.yearName);
        relativeLayout=itemView.findViewById(R.id.itemSubjectLayout);
    }
}
