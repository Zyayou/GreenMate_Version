package com.example.greenmate_front3;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CleanHouseRequest extends StringRequest {
    final static private String URL = "http://192.168.0.36/cleanhouse.php";
    private Map<String, String> map;

    public CleanHouseRequest(String cle_state, String cle_city, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("cle_state", cle_state);
        map.put("cle_city", cle_city);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
