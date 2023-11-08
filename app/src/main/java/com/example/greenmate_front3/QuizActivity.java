package com.example.greenmate_front3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class QuizActivity extends Activity {

    private Button gohome_button, goqna_button, gomy_button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_quiz );

        gohome_button = findViewById(R.id.gohome);
        goqna_button = findViewById(R.id.goqna);
        gomy_button = findViewById(R.id.gomy);

        gohome_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainpageActivity.class );
                startActivity(intent);
            }
        });

        goqna_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class );
                startActivity(intent);
            }
        });

        gomy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class );
                startActivity(intent);
            }
        });
    }
}
