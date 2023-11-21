package com.example.greenmate_front3.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.greenmate_front3.R;
import com.example.greenmate_front3.request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText loginId, loginPw;
    private Button loginBtn, joinBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginId = findViewById( R.id.loginId );
        loginPw = findViewById( R.id.loginPw );

        // 로그인 버튼
        loginBtn = findViewById( R.id.loginBtn );
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String UserEmail = loginId.getText().toString();
                String UserPwd = loginPw.getText().toString();

                //Toast.makeText( getApplicationContext(), String.format("로그인 버튼 누르기 %s", UserPwd), Toast.LENGTH_SHORT ).show();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText( getApplicationContext(), String.format("로그인 버튼 누르기 %s", UserPwd), Toast.LENGTH_SHORT ).show();
                        try {
                            //login_email.setText(response);  //테스트용
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if(success) {//로그인 성공시

                                //login_email.setText("들어오긴하는데..."); //태스트용
                                String UserId = jsonObject.getString( "m_id" );
                                String UserPwd = jsonObject.getString( "m_pw" );
                                String UserName = jsonObject.getString( "m_name" );
                                String UserEmail = jsonObject.getString("m_email");
                                String UserBirth = jsonObject.getString("m_birth");
                                String UserPhone = jsonObject.getString("m_phone");
                                String UserRpt = jsonObject.getString("m_rpt");
                                String UserImg = jsonObject.getString("m_img");
                                String UserGrade = jsonObject.getString("m_grade");

                                Toast.makeText( getApplicationContext(), String.format("%s님 환영합니다.", UserName), Toast.LENGTH_SHORT ).show();

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("m_id", UserId);
                                editor.putString("m_pw", UserPwd);
                                editor.putString("m_name", UserName);
                                editor.putString("m_email",UserEmail);
                                editor.putString("m_birth", UserBirth);
                                editor.putString("m_phone", UserPhone);
                                editor.putString("m_rpt", UserRpt);
                                editor.putString("m_img", UserImg);
                                editor.putString("m_grade", UserGrade);
                                editor.putBoolean("isLoggedIn", true);  // 로그인 상태를 저장
                                editor.apply();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class );
                                startActivity( intent );

                            } else {//로그인 실패시
                                //login_email.setText("ㅌㅌㅌㅌ..."); //테스트용
                                Toast.makeText( getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest( UserEmail, UserPwd, responseListener );
                RequestQueue queue = Volley.newRequestQueue( LoginActivity.this );
                queue.add( loginRequest );
            }
        });

        // 회원가입 버튼
        joinBtn = findViewById( R.id.joinBtn );
        joinBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this, JoinActivity.class );
                startActivity( intent );
            }
        });
    }
}
