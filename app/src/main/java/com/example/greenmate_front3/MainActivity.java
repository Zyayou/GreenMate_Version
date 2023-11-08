package com.example.greenmate_front3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    QuizFragment quizFragment;
    MyFragment myFragment;
    EcoDetailFragment ecoDetailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        quizFragment = new QuizFragment();
        myFragment = new MyFragment();
        ecoDetailFragment = new EcoDetailFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottomBar);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;
                } else if (itemId == R.id.quiz) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, quizFragment).commit();
                    return true;
                } else if (itemId == R.id.my) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();
                    return true;
                }
                return false;
            }
        });

    }
}