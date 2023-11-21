package com.example.greenmate_front3.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.greenmate_front3.R;
import com.example.greenmate_front3.activity.LoginActivity;
import com.example.greenmate_front3.activity.MainActivity;
import com.example.greenmate_front3.request.QuizCorrectRequest;
import com.example.greenmate_front3.request.QuizSelectRequest;
import com.example.greenmate_front3.request.QuizStateRequest;
import com.example.greenmate_front3.request.QuizWriteRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class QuizFragment extends Fragment {
    private Button yesBtn, noBtn;
    private TextView textQuiz;
    private String Quiz = "";
    private String Answer = "";

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //랜덤값에 따른 퀴즈 가져오기
    private void QuizSelect(){
        //퀴즈뽑아올 랜덤친구 들고오기
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String quizRand = preferences.getString("q_rand", "");
        String quizNum = Integer.toString(Integer.parseInt(quizRand)+1);
        Log.e("QuizRand : ", preferences.getString("q_rand", ""));

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //textQuiz.setText(response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        String quiz = jsonObject.getString("q_quiz");
                        String answer = jsonObject.getString("q_answer");

                        Quiz = quiz;
                        Answer = answer;

                        textQuiz.setText(Quiz);
                        //Toast.makeText(getActivity().getApplicationContext(), String.format("퀴즈 : %s", Quiz), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Error : QuizSelect", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        QuizSelectRequest quizSelectRequest = new QuizSelectRequest(quizNum, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(quizSelectRequest);
    }

    //퀴즈 답안쓰기
    private void QuizWrite(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        //SharedPreferences.Editor editor = preferences.edit();
        String quizState = preferences.getString("q_state", "");
        String userId = preferences.getString("m_id", "");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //textQuiz.setText(response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    //String exceptionTest = jsonObject.getString("exception");

                    //System.out.println("QuizWrite : " + exceptionTest);

                    if (success) {
                        //Toast.makeText(getActivity().getApplicationContext(), "퀴즈 답안 쓰기 성공", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity().getApplicationContext(), String.format("퀴즈 : %s", Quiz), Toast.LENGTH_SHORT).show();
                    } else {
                        if(jsonObject.has("exception")){
                            String exceptionMessage = jsonObject.getString("exception");
                            Log.e("Exception", exceptionMessage);
                        }
                        Toast.makeText(getActivity().getApplicationContext(), "Error : QuizWrite", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        QuizWriteRequest quizWriteRequest = new QuizWriteRequest(userId, quizState, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(quizWriteRequest);
    }

    //퀴즈 정답
    private void QuizCorrect(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        String userId = preferences.getString("m_id", "");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //textQuiz.setText(response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        String userGrade = preferences.getString("m_grade", "");
                        userGrade = Integer.toString( Integer.parseInt(userGrade) + 1);

                        editor.putString("m_grade", userGrade);
                        editor.apply();
                        //Toast.makeText(getActivity().getApplicationContext(), "정답~ ", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity().getApplicationContext(), String.format("퀴즈 : %s", Quiz), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Error : QuizCorrect", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        QuizCorrectRequest quizCorrectRequest = new QuizCorrectRequest(userId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(quizCorrectRequest);
    }

    //User의 퀴즈 상태 확인
    private void QuizStateSelect(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
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
                        Log.e("QuizState : ", preferences.getString("q_state",""));

                        //Toast.makeText(getActivity().getApplicationContext(), "User 퀴즈 상태 확인", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Error : QuizStateSelect", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        QuizStateRequest quizRequest = new QuizStateRequest(userId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(quizRequest);
    }

    private void newQuiz(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        String quizRand = preferences.getString("q_rand", "");
        int rand = (Integer.parseInt(quizRand)+1)%6;
        String quizRandPlus = Integer.toString(rand);

        editor.putString("q_rand", quizRandPlus);
        editor.apply();
        Log.e("QuizRand : ", preferences.getString("q_rand", ""));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_quiz, container, false);

        textQuiz = view.findViewById(R.id.textViewContent);

        //퀴즈 가져오기
        QuizSelect();
        //textQuiz.setText(Quiz);

        ////
        QuizStateSelect();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String quizState = preferences.getString("q_state", "");
        Log.e("QuizState : ", preferences.getString("q_state",""));

        if(quizState.equals("end")){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
        ////

        yesBtn = view.findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"YES",Toast.LENGTH_SHORT).show();

                if(Answer.equals("1")){
                    Toast.makeText(getActivity(),"정답입니다.",Toast.LENGTH_SHORT).show();
                    //정답
                    QuizWrite();
                    QuizCorrect();
                }else{
                    Toast.makeText(getActivity(),"오답입니다.",Toast.LENGTH_SHORT).show();
                    QuizWrite();
                }

                    QuizStateSelect();
                    /////////

                    newQuiz();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new QuizFragment())
                            .addToBackStack(null).commit();
                    //Toast.makeText(getActivity(),"다음 문제~",Toast.LENGTH_SHORT).show();
                //}
            }
        });

        noBtn = view.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"NO",Toast.LENGTH_SHORT).show();

                    if(Answer.equals("0")){
                        Toast.makeText(getActivity(),"정답입니다.",Toast.LENGTH_SHORT).show();
                        //정답
                        QuizWrite();
                        QuizCorrect();
                    }else{
                        Toast.makeText(getActivity(),"오답입니다.",Toast.LENGTH_SHORT).show();
                        QuizWrite();
                    }

                    QuizStateSelect();
                    //////

                    newQuiz();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new QuizFragment())
                            .addToBackStack(null).commit();
                    //Toast.makeText(getActivity(),"다음 문제~",Toast.LENGTH_SHORT).show();
                //}
            }
        });

        return view;
    }
}