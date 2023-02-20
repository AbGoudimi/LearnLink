package com.example.learnlink.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.learnlink.Adapter.SubjectAdapter;
import com.example.learnlink.ChangePasswordActivity;
import com.example.learnlink.EditProfileActivity;
import com.example.learnlink.LoginActivity;
import com.example.learnlink.Model.Student;
import com.example.learnlink.Model.Tutor;
import com.example.learnlink.R;
import com.example.learnlink.TeachableSubjectsActivity;
import com.example.learnlink.TutorListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button btnEditProfile;
    private Button tutorable;
    Student userProfile;
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        user= FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("users");


        final TextView tvFullName = getView().findViewById(R.id.tv_userFullName);
        final TextView tvEmail = getView().findViewById(R.id.tv_userEmail);
        final TextView tvPhoneNumber = getView().findViewById(R.id.tv_userPhone);
        tutorable = getView().findViewById(R.id.btn_editTeachableSubjects);
        btnEditProfile = getView().findViewById(R.id.btn_editProfile);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile= snapshot.getValue(Student.class);
                String FullName = userProfile.getFirstName() + " " + userProfile.getLastName();
                String email = userProfile.getEmail();
                String phonNumber = userProfile.getPhoneNumber();
                Boolean isTutor=userProfile.isIstutor();
                tvFullName.setText(FullName);
                tvEmail.setText(email);
                tvPhoneNumber.setText(phonNumber);
                if(!isTutor){
                    tutorable.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnEditProfile.setOnClickListener((v) ->  {
            Intent i = new Intent(getActivity(), EditProfileActivity.class);
            i.putExtra("firstName",userProfile.getFirstName());
            i.putExtra("lastName",userProfile.getLastName());
            i.putExtra("phoneNumber",userProfile.getPhoneNumber());
            i.putExtra("id",userProfile.getId());
            i.putExtra("isTutor",userProfile.isIstutor());
            startActivity(i);
        });
        Button btnChangePassword = getView().findViewById(R.id.btn_changePassword);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        Button logoutButton = getView().findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        tutorable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TeachableSubjectsActivity.class);
                startActivity(intent);
            }
        });
    }
}