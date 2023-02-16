package com.example.learnlink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learnlink.Model.Tutor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailTextView, passwordTextView, firstNameTextView, LastNameTextView;
    private Button btn_register;
    CheckBox TutorCheckBox;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        Button btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        emailTextView = findViewById(R.id.et_emailLogin);
        passwordTextView = findViewById(R.id.et_passwordLogin);
        firstNameTextView = findViewById(R.id.et_firstName);
        LastNameTextView = findViewById(R.id.et_lastName);
        TutorCheckBox = findViewById(R.id.cb_isTutor);
        Intent i = new Intent(this, LoginActivity.class);
        Intent i2 = new Intent(this, MainActivity.class);
        btn_login.setOnClickListener(view -> {
            startActivity(i);
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {

        // Take the value of two edit texts in Strings
        String email, password, firstName, lastName;
        boolean isTutor;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();
        firstName = firstNameTextView.getText().toString();
        lastName = LastNameTextView.getText().toString();
        isTutor = TutorCheckBox.isChecked();
        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter first Name!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter Last Name!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User account created successfully
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(firstName + " " + lastName)
                                    .build();
                            user.updateProfile(profileUpdates);
                            if (isTutor) {
                                // Add the user to the tutors table in the database
                                DatabaseReference tutorsRef = FirebaseDatabase.getInstance().getReference().child("tutors");
                                tutorsRef.child(user.getUid()).setValue(new Tutor(firstName, lastName));
                            }
                            Toast.makeText(getApplicationContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                    .show();
                            // if the user created intent to login activity
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // Registration failed
                            Toast.makeText(
                                            getApplicationContext(),
                                            "Registration failed!!"
                                                    + " Please try again later",
                                            Toast.LENGTH_LONG)
                                    .show();

                        }
                    }
                });

    }
}