package com.example.learnlink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.learnlink.Adapter.SubjectAdapter;
import com.example.learnlink.Adapter.TutorAdapter;
import com.example.learnlink.Interface.TutorRecyclerViewInterface;
import com.example.learnlink.Model.Student;
import com.example.learnlink.Model.Subject;
import com.example.learnlink.Model.Tutor;
import com.example.learnlink.Model.Year;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TutorListActivity extends AppCompatActivity implements TutorRecyclerViewInterface {
    List<Subject> subjects;
    List<Student> tutors;
    RecyclerView recyclerView;
    TutorAdapter adapter;
    String subjectName;
    DatabaseReference databaseReference;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);
        subjectName=getIntent().getStringExtra("subjectName");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query query = databaseReference.orderByChild("istutor").equalTo(true);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tutors = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Student tutor = userSnapshot.getValue(Student.class);

                    // check if the user has a tutor subject that matches the subjectName
                    if (hasMatchingTutorSubject(tutor, subjectName)) {
                        tutors.add(tutor);
                    }
                }
                recyclerView = TutorListActivity.this.findViewById(R.id.tutors_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(TutorListActivity.this));
                recyclerView.setHasFixedSize(true);
                adapter = new TutorAdapter(TutorListActivity.this,tutors,TutorListActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Error reading users", databaseError.toException());
            }
        });
        //dataInitilize();
    }
    private void dataInitilize() {
        subjects = new ArrayList<>();
        tutors = new ArrayList<>();
//        subjects.add(new Subject("c++",new Year("isi4")));
//        subjects.add(new Subject("c#",new Year("isi4")));
//        subjects.add(new Subject("java",new Year("isi4")));
//        subjects.add(new Subject("sgbd",new Year("isi4")));
//        tutors.add(new Tutor("Abdellah","Goudimi","AG@gmail.com","123","+212 607957797",subjects));
//        tutors.add(new Tutor("Samuel", "Johnson", "samuelj@gmail.com", "123", "+1 555 123 4567",subjects));
//        tutors.add(new Tutor("Emma", "Williams", "emmaw@gmail.com", "456", "+44 777 456 7890",subjects));
//        tutors.add(new Tutor("Michael", "Brown", "michaelb@gmail.com", "789", "+61 987 654 3210",subjects));
//        tutors.add(new Tutor("Sophia", "Lee", "sophial@gmail.com", "246", "+82 010 1234 5678",subjects));
//        tutors.add(new Tutor("David", "Smith", "davids@gmail.com", "369", "+34 678 901 2345",subjects));
    }
    private boolean hasMatchingTutorSubject(Student user, String subjectName) {
        List<Subject> tutorSubjects = user.getTutorSubjects();
        if(tutorSubjects==null){
            return false;
        }
        for (Subject tutorSubject : tutorSubjects) {
            if (tutorSubject != null && tutorSubject.getName().equals(subjectName)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void onItemClick(String tutor) {
        Intent i = new Intent(this, TutorDetailsActivity.class);
        i.putExtra("tutorId",tutor);
        startActivity(i);
    }
}