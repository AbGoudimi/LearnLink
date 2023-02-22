package com.example.learnlink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.learnlink.Adapter.MultiSubjectsAdapter;
import com.example.learnlink.Model.Student;
import com.example.learnlink.Model.Subject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeachableSubjectsActivity extends AppCompatActivity {

    private RecyclerView MultiSubjectsRecyclerView;
    private MultiSubjectsAdapter Adapter;
    private DatabaseReference Database;
    private FirebaseAuth Auth;
    DatabaseReference usersRef;
    DatabaseReference subjectsRef ;
    Student tutor;
    Button saveButton;
    ArrayList<Subject> subjects ;
    FirebaseUser currentUser;
    private boolean isTutorLoaded = false;
    private boolean isSubjectsLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachable_subjects);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        usersRef = FirebaseDatabase.getInstance().getReference("users");
        tutor= new Student();
        if (currentUser != null) {
            usersRef.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Student tut = new Student();
                        tut = dataSnapshot.getValue(Student.class);
                        tutor = tut;
                        isTutorLoaded = true;
                        setupRecyclerView();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        MultiSubjectsRecyclerView = findViewById(R.id.multi_subjects_recycler_view);
        subjectsRef = FirebaseDatabase.getInstance().getReference("subjects");
        subjects=new ArrayList<>();
        MultiSubjectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));




        //MultiSubjectsRecyclerView.setHasFixedSize(true);

        subjectsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Subject subject = snapshot.getValue(Subject.class);
                    subjects.add(subject);
                    isSubjectsLoaded = true;
                    setupRecyclerView();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        saveButton = findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTeachableSubjects();
            }
        });
    }

    private void setupRecyclerView() {
        if (isTutorLoaded && isSubjectsLoaded) {
            Adapter = new MultiSubjectsAdapter(TeachableSubjectsActivity.this, tutor, subjects);
            MultiSubjectsRecyclerView.setAdapter(Adapter);
            Adapter.notifyDataSetChanged();
        }
    }
    private void saveTeachableSubjects() {
        ArrayList<Subject> selectedSubjects = Adapter.getSelected();
        FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("users")
                .child(mFirebaseUser.getUid())
                .child("TutorSubjects");

        mDatabaseRef.setValue(selectedSubjects).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(TeachableSubjectsActivity.this, "Teachable subjects saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TeachableSubjectsActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}