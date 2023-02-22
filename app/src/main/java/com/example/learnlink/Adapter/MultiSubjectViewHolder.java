package com.example.learnlink.Adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnlink.Model.Student;
import com.example.learnlink.Model.Subject;
import com.example.learnlink.R;

import java.util.ArrayList;

public class MultiSubjectViewHolder extends RecyclerView.ViewHolder{
    TextView name,yearName;
    ImageView checkImg;
    public MultiSubjectViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.subjectNameMulti);
        yearName = itemView.findViewById(R.id.yearNameMulti);
        checkImg = itemView.findViewById(R.id.checkmarkMulti);

    }
    void bind(Subject subject,Student student){

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(student.getTutorSubjects()==null){
                    student.setTutorSubjects(new ArrayList<>());
                    student.addTutorSubject(subject);
                }else if(student.getTutorSubjects().contains(subject)) {
                    student.removeTutorSubject(subject);
                } else {
                    student.addTutorSubject(subject);
                }

                checkImg.setVisibility(student.getTutorSubjects().contains(subject) ? View.VISIBLE : View.GONE);
            }
        });

        if(student.getTutorSubjects()==null){
            checkImg.setVisibility(View.GONE);
        }else {
            boolean exist=false;
            for(int i=0;i<student.getTutorSubjects().size();i++){
                if(student.getTutorSubjects().get(i).getName().equals(subject.getName())){
                    Log.d("TAG", "true : " + subject.getName() +" / "+student.getTutorSubjects().get(i).getName());
                    exist = true;
                }
            }

            checkImg.setVisibility(exist ? View.VISIBLE : View.GONE);
        }
    }
}
