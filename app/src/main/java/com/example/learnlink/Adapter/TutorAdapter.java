package com.example.learnlink.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnlink.Interface.TutorRecyclerViewInterface;
import com.example.learnlink.Model.Tutor;
import com.example.learnlink.R;

import java.util.List;

public class TutorAdapter extends RecyclerView.Adapter<TutorViewHolder>{
    private final TutorRecyclerViewInterface tutorRecyclerViewInterface;
    Context context;
    List<Tutor> tutors;

    public TutorAdapter(Context context, List<Tutor> tutors , TutorRecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.tutors = tutors;
        this.tutorRecyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public TutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TutorViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tutor,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TutorViewHolder holder, int position) {
        holder.TutorEmail.setText(tutors.get(position).getEmail());
        holder.tutorName.setText(tutors.get(position).getFirstName());
        holder.TutorNumber.setText(tutors.get(position).getPhoneNumber());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorRecyclerViewInterface.onItemClick(tutors.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tutors.size();
    }

}

