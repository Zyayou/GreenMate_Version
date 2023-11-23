package com.example.greenmate_front3.eco;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EcoDetailRequest extends StringRequest {

    final static private String URL = "http://192.168.0.191/resDetail.php";
    private Map<String, String> map;

    public EcoDetailRequest(String rec_category, String rec_title, String rec_content, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("rec_category", rec_category);
        map.put("rec_title", rec_title);
        map.put("rec_content", rec_content);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
