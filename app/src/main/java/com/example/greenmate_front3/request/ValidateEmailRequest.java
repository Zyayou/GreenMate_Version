package com.example.greenmate_front3.request;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateEmailRequest extends StringRequest {

    final static  private String URL="http://192.168.0.191/UserValidateEmail.php";
    private Map<String, String> map;

    public ValidateEmailRequest(String m_email, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);

        map = new HashMap<>();
        map.put("m_email", m_email);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
