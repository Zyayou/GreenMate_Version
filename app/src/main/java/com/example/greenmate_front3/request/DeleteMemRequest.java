package com.example.greenmate_front3.request;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteMemRequest extends StringRequest {
    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://192.168.0.191/DeleteMember.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public DeleteMemRequest(String m_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("m_id", m_id);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
