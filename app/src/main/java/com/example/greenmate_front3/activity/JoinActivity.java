package com.example.greenmate_front3.activity;

import android.app.AlertDialog;
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
import com.example.greenmate_front3.R;
import com.example.greenmate_front3.request.RegisterRequest;
import com.example.greenmate_front3.request.ValidateEmailRequest;
import com.example.greenmate_front3.request.ValidateRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinActivity extends AppCompatActivity {
    private EditText joinId, joinEmail, joinPw, joinPwck, joinName, joinPhone, joinBirth;
    private Button checkIdBtn, checkEmailBtn, joinBtn, deleteBtn;
    private AlertDialog dialog;
    private boolean validate_id = false;
    private boolean validate_email = false;

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
        checkIdBtn = findViewById(R.id.checkIdBtn);
        checkIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m_id = joinId.getText().toString();
                if (validate_id) {
                    return; //검증 완료
                }

                if (m_id.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            //joinEmail.setText(response);  //테스트용
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                joinId.setEnabled(false); //아이디값 고정
                                validate_id = true; //검증 완료
                                checkIdBtn.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                dialog = builder.setMessage("이미 존재하는 아이디입니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ValidateRequest validateRequest = new ValidateRequest(m_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
                queue.add(validateRequest);
            }
        });

        // 중복확인 버튼 - 이메일 중복 체크 -> 코드수정필요
        checkEmailBtn = findViewById(R.id.checkEmailBtn);
        checkEmailBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String m_email = joinEmail.getText().toString();
                if (validate_email) {
                    return; //검증 완료
                }

                if (m_email.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("이메일을 입력하세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            //join_id.setText(response); //테스트용 코드
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 이메일입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                joinEmail.setEnabled(false); //아이디값 고정
                                validate_email = true; //검증 완료
                                checkEmailBtn.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                dialog = builder.setMessage("이미 존재하는 이메일입니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ValidateEmailRequest validateRequestEmail = new ValidateEmailRequest(m_email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
                queue.add(validateRequestEmail);
            }
        });

        // 가입 버튼
        joinBtn = findViewById( R.id.joinBtn );
        joinBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String m_id = joinId.getText().toString();
                final String m_pw = joinPw.getText().toString();
                final String PassCk = joinPwck.getText().toString();
                final String m_name = joinName.getText().toString();
                final String m_email = joinEmail.getText().toString();
                final String m_birth = joinBirth.getText().toString();
                final String m_phone = joinPhone.getText().toString();

                //아이디 중복체크 했는지 확인
                if (!validate_id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("중복된 아이디가 있는지 확인하세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                //이메일 중복체크 했는지 확인
                if (!validate_email) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("중복된 이메일이 있는지 확인하세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                //한 칸이라도 입력 안했을 경우
                if (m_id.equals("") || m_pw.equals("") || m_name.equals("") || m_email.equals("")  || m_birth.equals("")  || m_phone.equals("") ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                //비밀번호, 비밀번호 확인이 일치하지 않을 경우
                if(!(m_pw.equals(PassCk))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("비밀번호가 동일하지 않습니다.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                //DB등록
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );
                            //회원가입 성공시
                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("%s님 가입을 환영합니다.", m_name), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                startActivity(intent);

                                //회원가입 실패시
                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다. 형식에 맞게 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 Volley를 이용해서 요청
                RegisterRequest registerRequest = new RegisterRequest( m_id, m_pw, m_name, m_email, m_birth, m_phone, responseListener);
                RequestQueue queue = Volley.newRequestQueue( JoinActivity.this );
                queue.add( registerRequest );
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
