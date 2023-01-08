package com.example.learnlink;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectViewHolder extends RecyclerView.ViewHolder {
    TextView name,yearName;

    public SubjectViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        yearName = itemView.findViewById(R.id.yearName);
    }
}
