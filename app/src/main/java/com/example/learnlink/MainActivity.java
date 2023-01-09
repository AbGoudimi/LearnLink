package com.example.learnlink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.learnlink.Fragment.CalendarFragment;
import com.example.learnlink.Fragment.HomeFragment;
import com.example.learnlink.Fragment.NotificationsFragment;
import com.example.learnlink.Fragment.ProfileFragment;
import com.example.learnlink.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        showFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    showFragment(new HomeFragment());
                    break;
                case R.id.nav_calendar:
                    showFragment(new CalendarFragment());
                    break;
                case R.id.nav_notifications:
                    showFragment(new NotificationsFragment());
                    break;
                case R.id.nav_profile:
                    showFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }
    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }

}