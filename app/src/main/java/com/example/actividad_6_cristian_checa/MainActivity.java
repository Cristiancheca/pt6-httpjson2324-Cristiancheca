package com.example.actividad_6_cristian_checa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imageMLS;
    ImageButton imageNBA;
    ImageButton imageNFL;
    ImageButton imageNHL;
    ImageButton imageMLB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageMLS = findViewById(R.id.imageMLS);
        imageNBA = findViewById(R.id.imageNBA);
        imageNFL = findViewById(R.id.imageNFL);
        imageNHL = findViewById(R.id.imageNHL);
        imageMLB = findViewById(R.id.imageMLB);

        imageMLS.setOnClickListener(this);
        imageNBA.setOnClickListener(this);
        imageNFL.setOnClickListener(this);
        imageNHL.setOnClickListener(this);
        imageMLB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, TeamList.class);

        if (v.getId() == R.id.imageMLS) {
            intent.putExtra("abbreviature", "mls");
        } else if (v.getId() == R.id.imageNBA) {
            intent.putExtra("abbreviature", "nba");
        } else if (v.getId() == R.id.imageNFL) {
            intent.putExtra("abbreviature", "nfl");
        } else if (v.getId() == R.id.imageNHL) {
            intent.putExtra("abbreviature", "nhl");
        } else if (v.getId() == R.id.imageMLB) {
            intent.putExtra("abbreviature", "mlb");
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Toast.makeText(this, "API:" + sharedPreferences.getString("list_preference_1", "sports"), Toast.LENGTH_SHORT).show();
        String link = sharedPreferences.getString("list_preference_1", "sports");

        intent.putExtra("link", link);

        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.Options){
            Intent intent = new Intent(this, SettingsApp.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
