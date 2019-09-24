package com.github.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity
        implements View.OnClickListener {

    Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        btnStartGame = findViewById(R.id.btnNewGame);
        btnStartGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent gameScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(gameScreen);
    }
}
