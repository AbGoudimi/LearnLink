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
    Student tutor;
    Button saveButton;
    ArrayList<Subject> subjects = new ArrayList<>();
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachable_subjects);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        if (currentUser != null) {
            usersRef.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                         tutor = dataSnapshot.getValue(Student.class);
                        Log.d("TAG", "Tutor information: " + tutor.getFirstName());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        DatabaseReference subjectsRef = FirebaseDatabase.getInstance().getReference("subjects");
        subjectsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Subject subject = snapshot.getValue(Subject.class);
                    subjects.add(subject);
                    Log.d("TeachableSubjects", "Received subject: " + subject.getName());

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance().getReference();

        MultiSubjectsRecyclerView = findViewById(R.id.multi_subjects_recycler_view);
        MultiSubjectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MultiSubjectsRecyclerView.setHasFixedSize(true);
        Adapter = new MultiSubjectsAdapter(this, tutor,subjects);
        MultiSubjectsRecyclerView.setAdapter(Adapter);
        Adapter.notifyDataSetChanged();
        saveButton = findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTeachableSubjects();
            }
        });
    }


    private void saveTeachableSubjects() {
        ArrayList<Subject> selectedSubjects = Adapter.getSelected();
        FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Tutors")
                .child(mFirebaseUser.getUid())
                .child("teachableSubjects");
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