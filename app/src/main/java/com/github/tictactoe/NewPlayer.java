package com.github.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NewPlayer extends AppCompatActivity
        implements View.OnClickListener {

    Button btnSubmit;
    EditText newPlayer_EditText;
    PlayerDatabase pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newplayer_screen);

        btnSubmit = findViewById(R.id.btnSubmitNewPlayer);
        btnSubmit.setOnClickListener(this);

        newPlayer_EditText = findViewById(R.id.newPlayer_editText);

        pd = new PlayerDatabase(this);
    }


    @Override
    public void onClick(View view) {


        String playerName = newPlayer_EditText.getText().toString();

        pd.InsertPlayer(playerName);

        Intent splashScreen = new Intent(getApplicationContext(), SplashScreen.class);
        startActivity(splashScreen);

    }

}
