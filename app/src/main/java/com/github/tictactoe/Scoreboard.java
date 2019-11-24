package com.github.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Scoreboard extends AppCompatActivity {

    ListView scoreboard;
    PlayerDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard_screen);

        db = new PlayerDatabase(this);
        scoreboard = findViewById(R.id.players_scoreboard);


        populateScoreboard();


    }

    void populateScoreboard() {
        ArrayList<HashMap<String, String>> data = db.getPlayers();

        // create the resource, from, and to variables
        int resource = R.layout.listview_item;
        String[] from = {"name", "wins", "losses", "ties"};
        int[] to = {R.id.nameTextView, R.id.winsTextView, R.id.lossesTextView, R.id.tiesTextView};

        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(this, data, resource, from, to);
        scoreboard.setAdapter(adapter);
    }

}
