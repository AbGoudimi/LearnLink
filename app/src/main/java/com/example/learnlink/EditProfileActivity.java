package com.example.learnlink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        TextView tvFirstName = findViewById(R.id.et_firstName);
        TextView tvLastName = findViewById(R.id.et_lastName);
        TextView tvPhoneNumber = findViewById(R.id.et_phoneNumber);
        CheckBox cbIsTutor = findViewById(R.id.cb_isTutor);
        Button btnSave = findViewById(R.id.btn_save);

        Intent data = getIntent();
        String id = data.getStringExtra("id");
        String firstName = data.getStringExtra("firstName");
        String lastName = data.getStringExtra("lastName");
        String phoneNumber = data.getStringExtra("phoneNumber");
        Boolean isTutor = data.getBooleanExtra("isTutor",false);
        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvPhoneNumber.setText(phoneNumber);
        cbIsTutor.setChecked(isTutor);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(id);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newFirstName = tvFirstName.getText().toString().trim();
                String newLastName = tvLastName.getText().toString().trim();
                String newPhoneNumber = tvPhoneNumber.getText().toString().trim();
                Boolean newIsTutor = cbIsTutor.isChecked();
                if (TextUtils.isEmpty(firstName)) {
                    tvFirstName.setError("Please enter your first name.");
                }else if (TextUtils.isEmpty(lastName)) {
                    tvLastName.setError("Please enter your last name.");
                }else if (TextUtils.isEmpty(phoneNumber)) {
                    tvPhoneNumber.setError("Please enter your phone number.");
                }else{
                    // update the database with the new data
                    Map<String, Object> updates = new HashMap<>();
                    updates.put("firstName", newFirstName);
                    updates.put("lastName", newLastName);
                    updates.put("phoneNumber", newPhoneNumber);
                    updates.put("isTutor", newIsTutor);
                    mDatabase.updateChildren(updates);

                    // return to the previous activity
                    setResult(RESULT_OK);
                    finish();
                }

            }
        });
    }
}