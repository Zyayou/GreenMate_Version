package com.example.greenmate_front3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class EcodetailActivity extends Activity {
    private Button delete_button, gohome_button, goqna_button, gomy_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecodetail);

        delete_button = findViewById(R.id.delete_button);
        gohome_button = findViewById(R.id.gohome);
        goqna_button = findViewById(R.id.goqna);
        gomy_button = findViewById(R.id.gomy);

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EcosearchActivity.class );
                startActivity(intent);
            }
        });

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
