package com.example.greenmate_front3.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.greenmate_front3.fragment.HomeFragment;
import com.example.greenmate_front3.fragment.MyFragment;
import com.example.greenmate_front3.fragment.QuizFragment;
import com.example.greenmate_front3.eco.EcoDetailFragment;
import com.example.greenmate_front3.R;
import com.example.greenmate_front3.request.DeleteMemRequest;
import com.example.greenmate_front3.request.QuizStateRequest;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    QuizFragment quizFragment;
    MyFragment myFragment;
    EcoDetailFragment ecoDetailFragment;

    //User의 퀴즈 상태 확인
    private void QuizStateSelect(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        String userId = preferences.getString("m_id", "");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //login_email.setText(response);  //테스트용
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    String quizState = jsonObject.getString("q_state");

                    if (success) {
                        editor.putString("q_state", quizState);
                        editor.apply();
                    } else {
                        Toast.makeText(getApplicationContext(), "퀴즈 상태 확인 실패", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        QuizStateRequest quizRequest = new QuizStateRequest(userId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(quizRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        quizFragment = new QuizFragment();
        myFragment = new MyFragment();
        ecoDetailFragment = new EcoDetailFragment();

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        QuizStateSelect();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        // 화면 전환 프래그먼트 선언 및 초기 화면 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottomBar);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //버튼 클릭 관련
                int itemId = item.getItemId();
                if (itemId == R.id.home) { ////////////홈
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;
                } else if (itemId == R.id.quiz) { //////////퀴즈

                    QuizStateSelect();

                    //퀴즈 상태를 들고 옴
                    String quizState = preferences.getString("q_state", "");

                    if(!quizState.equals("end")) {

                        //Quiz 랜덤값 발생
                        int QuizRandom = random.nextInt(6);
                        //int QuizRandom = random.nextInt(문제 개수)+1;
                        editor.putString("q_rand", Integer.toString(QuizRandom));
                        editor.apply();

                        //화면 전환
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, quizFragment).commit();
                        return true;
                    }
                    else{
                        Toast.makeText( getApplicationContext(), String.format("오늘의 퀴즈를 다 풀으셨습니다. 내일 다시 도전하세요! %s", quizState), Toast.LENGTH_SHORT ).show();
                    }
                } else if (itemId == R.id.my) { /////////////// 마이페이지
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }
}