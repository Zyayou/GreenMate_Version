package com.example.greenmate_front3.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
// 회원가입 값 요청
// 수정필요!
public class RegisterRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://192.168.0.191/Register.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RegisterRequest(String m_id, String m_pw, String m_name, String m_email, String m_birth, String m_phone, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("m_id", m_id);
        map.put("m_name", m_name);
        map.put("m_pw", m_pw);
        map.put("m_email", m_email);
        map.put("m_birth", m_birth);
        map.put("m_phone", m_phone);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}