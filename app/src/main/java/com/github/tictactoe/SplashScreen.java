package com.github.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity
        implements View.OnClickListener {

    Button btnStartGame;
    Button btnViewScoreboard;
    Button btnSelectPlayer1;
    Button btnSelectPlayer2;
    Button btnNewPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        btnStartGame = findViewById(R.id.btnNewGame);
        btnStartGame.setOnClickListener(this);

        btnViewScoreboard = findViewById(R.id.btnViewScoreboard);
        btnViewScoreboard.setOnClickListener(this);

        btnSelectPlayer1 = findViewById(R.id.btnSelectPlayer1);
        btnSelectPlayer1.setOnClickListener(this);

        btnSelectPlayer2 = findViewById(R.id.btnSelectPlayer2);
        btnSelectPlayer2.setOnClickListener(this);

        btnNewPlayer = findViewById(R.id.btnNewPlayer);
        btnNewPlayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.btnNewGame:

                if (VarTempStorage.player1Name == "" || VarTempStorage.player2Name == "") {

                    if (VarTempStorage.player1Name == "") {
                        Intent player1Select = new Intent(getApplicationContext(), Player1Select.class);
                        startActivity(player1Select);
                    }

                    if (VarTempStorage.player2Name == "") {
                        Intent player2Select = new Intent(getApplicationContext(), Player2Select.class);
                        startActivity(player2Select);
                    }

                } else {
                    Intent gameScreen = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(gameScreen);
                }

                break;

            case R.id.btnViewScoreboard:
                Intent scoreboard = new Intent(getApplicationContext(), Scoreboard.class);
                startActivity(scoreboard);
                break;

            case R.id.btnSelectPlayer1:
                Intent player1Select = new Intent(getApplicationContext(), Player1Select.class);
                startActivity(player1Select);
                break;

            case R.id.btnSelectPlayer2:
                Intent player2Select = new Intent(getApplicationContext(), Player2Select.class);
                startActivity(player2Select);
                break;


            case R.id.btnNewPlayer:
                Intent newPlayer = new Intent(getApplicationContext(), NewPlayer.class);
                startActivity(newPlayer);
                break;
        }


    }
}
