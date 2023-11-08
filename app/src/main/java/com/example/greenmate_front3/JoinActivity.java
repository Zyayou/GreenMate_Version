package com.example.greenmate_front3;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {
    private EditText joinId, joinEmail, joinPw, joinPwck, joinName, joinPhone, joinBirth;
    private Button checkBtn, joinBtn, deleteBtn;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_join );

        //아이디값 찾아주기
        joinId = findViewById(R.id.joinId);
        joinEmail = findViewById( R.id.joinEmail );
        joinPw = findViewById( R.id.joinPw );
        joinPwck = findViewById(R.id.joinPwck);
        joinName = findViewById( R.id.joinName );
        joinPhone = findViewById(R.id.joinPhone);
        joinBirth = findViewById(R.id.joinBirth);

        // 중복확인 버튼 - 아이디 중복 체크 -> 코드수정필요
        checkBtn = findViewById(R.id.checkIdBtn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"아이디 중복 버튼",Toast.LENGTH_SHORT).show();
            }
        });

        // 중복확인 버튼 - 이메일 중복 체크 -> 코드수정필요
        checkBtn = findViewById(R.id.checkEmailBtn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"이메일 중복 버튼",Toast.LENGTH_SHORT).show();
            }
        });

        // 중복확인 버튼 - 전화번호 중복 체크 -> 코드수정필요
        checkBtn = findViewById(R.id.checkPhoneBtn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"전화번호 중복 버튼",Toast.LENGTH_SHORT).show();
            }
        });

        // 가입 버튼
        joinBtn = findViewById( R.id.joinBtn );
        joinBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class );
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"가입되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });


        // 취소 버튼
        deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class );
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"가입이 취소되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
