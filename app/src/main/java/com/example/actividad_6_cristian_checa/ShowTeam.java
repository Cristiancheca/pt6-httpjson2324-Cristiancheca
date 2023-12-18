package com.example.actividad_6_cristian_checa;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class ShowTeam extends AppCompatActivity {

    private RequestQueue queue = null;
    private Data elements;

    ImageView teamImage;
    TextView txtViewTitles;
    TextView txtViewStadium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_team);

        Bundle extras = getIntent().getExtras();

        teamImage = findViewById(R.id.TeamImage);
        txtViewTitles =  findViewById(R.id.txtViewTitles);
        txtViewStadium = findViewById(R.id.txtViewStadium);

        Intent intent = getIntent();
        String URL = intent.getStringExtra("URL");
        //String image = intent.getStringExtra("image");
        URL = URL.toLowerCase();
        loadData(URL);
        new DownloadImageTask().execute(URL+".png");


    }

    private void loadData(String url) {

        url += ".json";

        if ( queue == null )
            queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            Data data = new Data(
                                    jsonArray.getJSONObject(0).getInt("titles"),
                                    jsonArray.getJSONObject(0).getString("team_stadium")
                            );
                            txtViewTitles.setText("Titles: " + data.getTitles());
                            txtViewStadium.setText("Stadium: " + data.getTeam_stadium().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ShowTeam.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                    }
                });


        queue.add(request);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            //urls += ".png";
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new URL(imageUrl).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                teamImage.setImageBitmap(result);
            }
        }
    }

}
