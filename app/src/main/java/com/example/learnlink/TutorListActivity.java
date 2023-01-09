package com.example.learnlink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.learnlink.Adapter.SubjectAdapter;
import com.example.learnlink.Adapter.TutorAdapter;
import com.example.learnlink.Interface.TutorRecyclerViewInterface;
import com.example.learnlink.Model.Subject;
import com.example.learnlink.Model.Tutor;
import com.example.learnlink.Model.Year;

import java.util.ArrayList;
import java.util.List;

public class TutorListActivity extends AppCompatActivity implements TutorRecyclerViewInterface {
    List<Subject> subjects;
    List<Tutor> tutors;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);

        dataInitilize();
        recyclerView = this.findViewById(R.id.tutors_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        TutorAdapter adapter = new TutorAdapter(this,tutors,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void dataInitilize() {
        subjects = new ArrayList<Subject>();
        tutors = new ArrayList<Tutor>();
        subjects.add(new Subject("c++",new Year("isi4")));
        subjects.add(new Subject("c#",new Year("isi4")));
        subjects.add(new Subject("java",new Year("isi4")));
        subjects.add(new Subject("sgbd",new Year("isi4")));
        tutors.add(new Tutor("Abdellah","Goudimi","AG@gmail.com","123","+212 607957797",subjects));
        tutors.add(new Tutor("Samuel", "Johnson", "samuelj@gmail.com", "123", "+1 555 123 4567",subjects));
        tutors.add(new Tutor("Emma", "Williams", "emmaw@gmail.com", "456", "+44 777 456 7890",subjects));
        tutors.add(new Tutor("Michael", "Brown", "michaelb@gmail.com", "789", "+61 987 654 3210",subjects));
        tutors.add(new Tutor("Sophia", "Lee", "sophial@gmail.com", "246", "+82 010 1234 5678",subjects));
        tutors.add(new Tutor("David", "Smith", "davids@gmail.com", "369", "+34 678 901 2345",subjects));
    }

    @Override
    public void onItemClick(Tutor tutor) {
        Intent i = new Intent(this, TutorDetailsActivity.class);
        startActivity(i);
    }
}