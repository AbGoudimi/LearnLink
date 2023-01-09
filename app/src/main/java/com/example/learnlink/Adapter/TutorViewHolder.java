package com.example.learnlink.Adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnlink.R;

public class TutorViewHolder extends RecyclerView.ViewHolder{
    TextView tutorName,TutorEmail,TutorNumber;
    RelativeLayout relativeLayout;
    public TutorViewHolder(@NonNull View itemView) {
        super(itemView);
        tutorName = itemView.findViewById(R.id.tutorName);
        TutorEmail = itemView.findViewById(R.id.tutorEmail);
        TutorNumber=itemView.findViewById(R.id.tutorPhoneNumber);
        relativeLayout=itemView.findViewById(R.id.itemTutorLayout);
    }
}
