package com.github.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Player2Select extends AppCompatActivity {

    ListView lv;
    PlayerDatabase db;
    String selectedPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectplayer2_screen);


        lv = findViewById(R.id.SelectPlayer2_playerListView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPlayer =  lv.getItemAtPosition(i).toString();
                returnToSplash();
            }
        });

        db = new PlayerDatabase(this);

        fillPlayerList();


    }

    void fillPlayerList() {

        ArrayList<HashMap<String, String>> data = db.getPlayers();

        // create the resource, from, and to variables
        int resource = R.layout.listview_item;
        String[] from = {"name"};
        int[] to = {R.id.nameTextView};

        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(this, data, resource, from, to);
        lv.setAdapter(adapter);
    }


    void returnToSplash() {

        VarTempStorage.player2Name = selectedPlayer;

        Intent splashScreen = new Intent(getApplicationContext(), SplashScreen.class);
        startActivity(splashScreen);

    }


}
