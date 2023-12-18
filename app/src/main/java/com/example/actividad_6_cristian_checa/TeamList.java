package com.example.actividad_6_cristian_checa;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeamList extends AppCompatActivity {

    public String link;
    public ArrayList<Team>  TeamElements = new ArrayList<Team>();
    private RequestQueue queue = null;
    private String URL = "";
    public List<Team> getElements() {
        return TeamElements;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        Intent intent = getIntent();
        String abbreviature = intent.getStringExtra("abbreviature");
        String link = intent.getStringExtra("link");

        URL = "https://www.vidalibarraquer.net/android/" + link + "/" + abbreviature ;
        TeamAdapter adapter = new TeamAdapter(TeamElements, URL);

        //URL += ".json";

        RecyclerView viewList = findViewById(R.id.TeamsList);

        System.out.println("TEAM LIST: " + URL);
        loadData(viewList, URL);
        viewList.setAdapter(adapter);

    }

    private void loadData(RecyclerView viewList, String url) {
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
            url += ".json";

            JsonObjectRequest request = new JsonObjectRequest(url,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                TeamElements.clear();
                                JSONArray jsonArray = response.getJSONArray("teams");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Team team = new Team(
                                            jsonArray.getJSONObject(i).getInt("team_id"),
                                            jsonArray.getJSONObject(i).getString("team_name"),
                                            jsonArray.getJSONObject(i).getString("team_abbreviation")
                                    );
                                    TeamElements.add(team);
                                }

                                // Cambiar el título de la actividad
                                viewList.getAdapter().notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(TeamList.this, "Error en obtener datos", Toast.LENGTH_SHORT).show();
                        }
                    });
            queue.add(request);
        }
    }

}