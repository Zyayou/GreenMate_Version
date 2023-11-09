package com.example.greenmate_front3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.greenmate_front3.LoginRequest;
import com.example.greenmate_front3.R;
import com.example.greenmate_front3.RegisterActivity;

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

        //회원가입 버튼
        joinBtn = findViewById( R.id.joinBtn );
        joinBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this, RegisterActivity.class );
                startActivity( intent );
            }
        });

        //로그인 버튼
        loginBtn = findViewById( R.id.loginBtn );
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserId = loginId.getText().toString();
                String UserPwd = loginPw.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //login_email.setText(response);  //테스트용
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if(success) {//로그인 성공시

                                //login_email.setText("들어오긴하는데..."); //태스트용
                                String UserEmail = jsonObject.getString( "m_id" );
                                String UserPwd = jsonObject.getString( "m_pw" );
                                String UserName = jsonObject.getString( "m_name" );

                                Toast.makeText( getApplicationContext(), String.format("%s님 환영합니다.", UserName), Toast.LENGTH_SHORT ).show();

                                //이전에 메인 페이지가 없을때의 페이지 이동 코드
                                //Intent intent = new Intent( LoginActivity.this, MainActivity.class );
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class );

                                intent.putExtra( "m_id", UserEmail );
                                intent.putExtra( "m_pw", UserPwd );
                                intent.putExtra( "m_name", UserName );

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
                LoginRequest loginRequest = new LoginRequest( UserId, UserPwd, responseListener );
                RequestQueue queue = Volley.newRequestQueue( LoginActivity.this );
                queue.add( loginRequest );
                //로그인 기능이 없을 때 바로 창 이동하던 코드
//                Intent intent = new Intent(getApplicationContext(), MainpageActivity.class );
//                startActivity(intent);

            }
        });
    }
}
