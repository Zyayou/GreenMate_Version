package com.example.greenmate_front3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greenmate_front3.R;

public class WithdrawalActivity extends AppCompatActivity {
    private Button yesBtn, noBtn;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        // 네 버튼
        yesBtn = findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WithdrawalActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "성공적으로 탈퇴되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 아니오 버튼
        noBtn = findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WithdrawalActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "탈퇴를 취소하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
