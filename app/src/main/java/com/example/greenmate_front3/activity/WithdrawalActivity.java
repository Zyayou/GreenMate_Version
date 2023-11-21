package com.example.greenmate_front3.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.greenmate_front3.R;
import com.example.greenmate_front3.request.DeleteMemRequest;
import com.example.greenmate_front3.request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class WithdrawalActivity extends AppCompatActivity {
    private Button yesBtn, noBtn;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userId = preferences.getString("m_id", "");

        // 네 버튼
        yesBtn = findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //login_email.setText(response);  //테스트용
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {//로그인 성공시
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.remove("m_id");
                                editor.remove("m_pw");
                                editor.remove("m_name");
                                editor.remove("m_email");
                                editor.remove("m_birth");
                                editor.remove("m_phone");
                                editor.remove("m_rpt");
                                editor.remove("m_img");
                                editor.remove("m_grade");
                                editor.remove("q_state");
                                editor.remove("q_rand");
                                editor.putBoolean("isLoggedIn", false);  // 로그인 상태를 저장
                                editor.apply();

                                Intent intent = new Intent(WithdrawalActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "성공적으로 탈퇴되었습니다.", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "회원 탈퇴에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteMemRequest deleteRequest = new DeleteMemRequest(userId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(WithdrawalActivity.this);
                queue.add(deleteRequest);
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
