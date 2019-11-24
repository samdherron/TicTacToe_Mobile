package com.github.tictactoe;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Rectangle;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    //region Variables
    Button btnSpace1;
    Button btnSpace2;
    Button btnSpace3;
    Button btnSpace4;
    Button btnSpace5;
    Button btnSpace6;
    Button btnSpace7;
    Button btnSpace8;
    Button btnSpace9;
    Button btnNewGame;

    PlayerDatabase db;

    int moveCounter = 0;
    TextView turnTextDisplay;
    Map<Integer, String> shapeList = new HashMap<Integer, String>();
    Button[] buttonList = new Button[9];
    String statusCheck = "";
    TextView player1Display;
    TextView player2Display;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Setup Buttons and Button Event Handlers
        btnSpace1 = findViewById(R.id.btnSpace1);
        btnSpace1.setOnClickListener(this);

        btnSpace2 = findViewById(R.id.btnSpace2);
        btnSpace2.setOnClickListener(this);

        btnSpace3 = findViewById(R.id.btnSpace3);
        btnSpace3.setOnClickListener(this);

        btnSpace4 = findViewById(R.id.btnSpace4);
        btnSpace4.setOnClickListener(this);

        btnSpace5 = findViewById(R.id.btnSpace5);
        btnSpace5.setOnClickListener(this);

        btnSpace6 = findViewById(R.id.btnSpace6);
        btnSpace6.setOnClickListener(this);

        btnSpace7 = findViewById(R.id.btnSpace7);
        btnSpace7.setOnClickListener(this);

        btnSpace8 = findViewById(R.id.btnSpace8);
        btnSpace8.setOnClickListener(this);

        btnSpace9 = findViewById(R.id.btnSpace9);
        btnSpace9.setOnClickListener(this);

        btnNewGame = findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(this);

        buttonList[0] = btnSpace1;
        buttonList[1] = btnSpace2;
        buttonList[2] = btnSpace3;
        buttonList[3] = btnSpace4;
        buttonList[4] = btnSpace5;
        buttonList[5] = btnSpace6;
        buttonList[6] = btnSpace7;
        buttonList[7] = btnSpace8;
        buttonList[8] = btnSpace9;

        //endregion

        turnTextDisplay = findViewById(R.id.playerTurnDisplay);

        player1Display = findViewById(R.id.player1Name);
        player2Display = findViewById(R.id.player2Name);

        player1Display.setText(VarTempStorage.player1Name);
        player2Display.setText(VarTempStorage.player2Name);

        db = new PlayerDatabase(this);

    }


    @Override
    public void onClick(View view) {

        // Gets the id of the button that was clicked
        int id = view.getId();

        if (id == R.id.btnNewGame) {

            startNewGame();

        }


        if (id != R.id.btnNewGame) {

        /* Checks whether or not the space clicked on
           has a shape inside already  */

            Boolean emptySpace = checkForEmptySpace(id);

                if (emptySpace && statusCheck == "") {

                    //Gets which shape to draw inside of the space based on the number of moves
                    String shapeText = getPlayerTurnShape(moveCounter);

                    //Draws the shape onto the specific button, also colours the button based on moveCounter
                    drawSymbol(id, shapeText);

                    //Updates the HashMap with the new Button value to keep track of all spaces
                    shapeList.put(id, shapeText);

                    //Increment moveCounter to change to the other player's turn
                    moveCounter++;

                    //Updates the Text displaying which Player's turn it is
                    updateTurnDisplay();

                } else {
                    Log.println(Log.ASSERT, "Already has shape", "The space you selected is already filled.");
                }


            }

            /* Performs a Status Check to see if the a Player has won or
                if the game is tied */

            if (moveCounter >= 5) {
                statusCheck = gameCheck();

                if (statusCheck == "X Win") {

                    turnTextDisplay.setTextColor(Color.rgb(231, 29, 54));
                    turnTextDisplay.setBackgroundColor(Color.argb(50, 0, 0, 0));
                    turnTextDisplay.setText("Player 1 (X) has won the game!");

                } else if (statusCheck == "O Win") {
                    turnTextDisplay.setTextColor(Color.rgb(0, 185, 255));
                    turnTextDisplay.setBackgroundColor(Color.argb(50, 0, 0, 0));
                    turnTextDisplay.setText("Player 2 (O) has won the game!");

                }

            }


        }



        public void startNewGame() {

            int defaultButtonBG = android.R.drawable.btn_default;
            moveCounter = 0;

            //Iterate through Buttons and Reset them
            for (Button button : buttonList) {
                button.setBackgroundColor(Color.TRANSPARENT);
                button.setBackgroundResource(defaultButtonBG);
                button.setText("");
            }

            shapeList.clear();
            turnTextDisplay.setTextColor(000);
            turnTextDisplay.setText("Player 1's Turn (X)");
            turnTextDisplay.setBackgroundColor(0);
            statusCheck = "";

        }

        //region Button Methods
    public Boolean checkForEmptySpace(int id) {
        Boolean val = false;
        String spaceValue = "";

        if (shapeList.containsKey(id)) {

            spaceValue = shapeList.get(id);
        }

        if (spaceValue.isEmpty()) {
            val = true;
        }

        return val;
    }


        public String getPlayerTurnShape(int moveCounter){
        String val = "";

        //Checks for even number to set X, if odd number will set Y
        if (moveCounter % 2 == 0) {
            val = "X";
        } else {
            val = "O";
        }

        return val;
        }

        public void drawSymbol(int id, String spaceText) {

        int blueColour = Color.rgb(0, 168, 232);
        int redColour = Color.rgb(231, 29, 54);

        switch (id) {
            case R.id.btnSpace1:
                btnSpace1.setText(spaceText);

                if (moveCounter % 2 == 0) {
                    btnSpace1.setBackgroundColor(redColour);
                } else {
                    btnSpace1.setBackgroundColor(blueColour);
                }

                break;

            case R.id.btnSpace2:
                btnSpace2.setText(spaceText);

                if (moveCounter % 2 == 0) {
                    btnSpace2.setBackgroundColor(redColour);
                } else {
                    btnSpace2.setBackgroundColor(blueColour);
                }

                break;

            case R.id.btnSpace3:
                btnSpace3.setText(spaceText);

                if (moveCounter % 2 == 0) {
                    btnSpace3.setBackgroundColor(redColour);
                } else {
                    btnSpace3.setBackgroundColor(blueColour);
                }

                break;

            case R.id.btnSpace4:
                btnSpace4.setText(spaceText);

                if (moveCounter % 2 == 0) {
                    btnSpace4.setBackgroundColor(redColour);
                } else {
                    btnSpace4.setBackgroundColor(blueColour);
                }

                break;

            case R.id.btnSpace5:
                btnSpace5.setText(spaceText);

                if (moveCounter % 2 == 0) {
                    btnSpace5.setBackgroundColor(redColour);
                } else {
                    btnSpace5.setBackgroundColor(blueColour);
                }

                break;

            case R.id.btnSpace6:
                btnSpace6.setText(spaceText);

                if (moveCounter % 2 == 0) {
                    btnSpace6.setBackgroundColor(redColour);
                } else {
                    btnSpace6.setBackgroundColor(blueColour);
                }

                break;

            case R.id.btnSpace7:
                btnSpace7.setText(spaceText);

                if (moveCounter % 2 == 0) {
                    btnSpace7.setBackgroundColor(redColour);
                } else {
                    btnSpace7.setBackgroundColor(blueColour);
                }

                break;

            case R.id.btnSpace8:
                btnSpace8.setText(spaceText);

                if (moveCounter % 2 == 0) {
                    btnSpace8.setBackgroundColor(redColour);
                } else {
                    btnSpace8.setBackgroundColor(blueColour);
                }

                break;

            case R.id.btnSpace9:
                btnSpace9.setText(spaceText);

                if (moveCounter % 2 == 0) {
                    btnSpace9.setBackgroundColor(redColour);
                } else {
                    btnSpace9.setBackgroundColor(blueColour);
                }

                break;
        }
    }

    //endregion

    /** */
    public String gameCheck() {
        String returnVal = "";
        int counter = 0;
        String xVal = "X";
        String oVal = "O";


            // region Condition Checks

            //3 Across
            if (buttonList[0].getText() == buttonList[1].getText() && buttonList[1].getText() == buttonList[2].getText()) {

                if (buttonList[0].getText() == "X") {

                    returnVal = "X Win";

                } else if (buttonList[0].getText() == "O") {

                    returnVal = "O Win";

                }
            }

            if (buttonList[3].getText() == buttonList[4].getText() && buttonList[4].getText() == buttonList[5].getText()) {

                if (buttonList[3].getText() == "X") {

                    returnVal = "X Win";

                } else if (buttonList[3].getText() == "O") {

                    returnVal = "O Win";

                }
            }

            if (buttonList[6].getText() == buttonList[7].getText() && buttonList[7].getText() == buttonList[8].getText()) {

                if (buttonList[6].getText() == "X") {

                    returnVal = "X Win";

                } else if (buttonList[6].getText() == "O") {

                    returnVal = "O Win";

                }
            }

            // 3 Down
            if (buttonList[0].getText() == buttonList[3].getText() && buttonList[3].getText() == buttonList[6].getText()) {

                if (buttonList[0].getText() == "X") {

                    returnVal = "X Win";

                } else if (buttonList[0].getText() == "O") {

                    returnVal = "O Win";

                }
            }

            if (buttonList[1].getText() == buttonList[4].getText() && buttonList[4].getText() == buttonList[7].getText()) {

                if (buttonList[1].getText() == "X") {

                    returnVal = "X Win";

                } else if (buttonList[1].getText() == "O") {

                    returnVal = "O Win";

                }
            }

            if (buttonList[2].getText() == buttonList[5].getText() && buttonList[5].getText() == buttonList[8].getText()) {

                if (buttonList[2].getText() == "X") {

                    returnVal = "X Win";

                } else if (buttonList[2].getText() == "O") {

                    returnVal = "O Win";

                }
            }

            //Across
            if (buttonList[0].getText() == buttonList[4].getText() && buttonList[4].getText() == buttonList[8].getText()) {

                if (buttonList[0].getText() == "X") {

                    returnVal = "X Win";

                } else if (buttonList[0].getText() == "O") {

                    returnVal = "O Win";

                }
            }

            if (buttonList[2].getText() == buttonList[4].getText() && buttonList[4].getText() == buttonList[6].getText()) {

                if (buttonList[2].getText() == "X") {

                    returnVal = "X Win";

                } else if (buttonList[2].getText() == "O") {

                    returnVal = "O Win";

                }
            }

            //endregion

        return returnVal;

    }

    public void updateTurnDisplay() {

        /* Gives a slight delay making it feel smoother
            and not as snappy when changing text    */
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (moveCounter % 2 == 0) {
         turnTextDisplay.setText("Player 1's Turn (X)");
        }

        else {
        turnTextDisplay.setText("Player 2's Turn (O)");
        }

    }




}
