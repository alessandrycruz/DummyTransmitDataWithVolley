package com.alobot.dummytransmitdatawithvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.0.67/loginServices/RestServiceImpl.svc/json/245";

        // Request a string response from the provided URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(MainActivity.class.getSimpleName(), response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject2 = jsonObject.getJSONObject("GetDataResult");

                            String jsonId = jsonObject2.getString("Id");
                            String jsonName = jsonObject2.getString("Name");

                            Log.i(MainActivity.class.getSimpleName(), "ID: " + jsonId);
                            Log.i(MainActivity.class.getSimpleName(), "Name: " + jsonName);
                        } catch (Exception e) {
                            Log.e(MainActivity.class.getSimpleName(), "Error: " + e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(MainActivity.class.getSimpleName(), error.toString());
            }
        });

        // Add the request to the RequestQueue
        queue.add(stringRequest);
    }
}
