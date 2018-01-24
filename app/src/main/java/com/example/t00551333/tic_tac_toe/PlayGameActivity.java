package com.example.t00551333.tic_tac_toe;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Random;
import java.util.StringTokenizer;
//Activity where the actual gameplay takes place
public class PlayGameActivity extends MainActivity implements View.OnClickListener {

    Button button01, button02, button03, button04, button05;
    Button button06, button07, button08, button09, button10;
    TextView playerScore, compScore;
    Boolean T11 = false, T12 = false, T13 = false, T21 = false;
    Boolean T22 = false, T23 = false, T31 = false, T32 = false, T33 = false;
    Boolean player = true;
    int randNum, pWins = 0, cWins = 0, cWins2 = 0, pWins2 = 0;
    String PLAYERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
//Getting data from shared preferences
        SharedPreferences prefsName = this.getSharedPreferences("NAMES", MODE_PRIVATE);
        SharedPreferences prefsScore = this.getSharedPreferences("SCORES", MODE_PRIVATE);
        cWins2 = prefsScore.getInt("ANDROID", cWins);
        PLAYERNAME = prefsName.getString("MostRecent", PLAYERNAME);
//Setting player name to "Anonymous" if the player hasn't entered their own name yet
        if (PLAYERNAME == null)
            PLAYERNAME  = "Anonymous";
        pWins2 = prefsScore.getInt(PLAYERNAME, pWins);

        button01 = (Button) findViewById(R.id.button01);
        button01.setOnClickListener(this);
        button02 = (Button) findViewById(R.id.button02);
        button02.setOnClickListener(this);
        button03 = (Button) findViewById(R.id.button03);
        button03.setOnClickListener(this);
        button04 = (Button) findViewById(R.id.button04);
        button04.setOnClickListener(this);
        button05 = (Button) findViewById(R.id.button05);
        button05.setOnClickListener(this);
        button06 = (Button) findViewById(R.id.button06);
        button06.setOnClickListener(this);
        button07 = (Button) findViewById(R.id.button07);
        button07.setOnClickListener(this);
        button08 = (Button) findViewById(R.id.button08);
        button08.setOnClickListener(this);
        button09 = (Button) findViewById(R.id.button09);
        button09.setOnClickListener(this);
        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(this);
        playerScore = (TextView) findViewById(R.id.textView03);
        compScore = (TextView) findViewById(R.id.textView04);

//Setting the game board to its saved state (If one exists)
        if (savedInstanceState != null){
            clearBoard();
            pWins = savedInstanceState.getInt("Player", pWins);
            cWins = savedInstanceState.getInt("Comp", cWins);
            playerScore.setText(PLAYERNAME + ": " + pWins);
            compScore.setText("Android: " + cWins);
            button01.setText(savedInstanceState.getString("B11", ""));
            button02.setText(savedInstanceState.getString("B12", ""));
            button03.setText(savedInstanceState.getString("B13", ""));
            button04.setText(savedInstanceState.getString("B21", ""));
            button05.setText(savedInstanceState.getString("B22", ""));
            button06.setText(savedInstanceState.getString("B23", ""));
            button07.setText(savedInstanceState.getString("B31", ""));
            button08.setText(savedInstanceState.getString("B32", ""));
            button09.setText(savedInstanceState.getString("B33", ""));
            button01.setTextSize(40);
            button02.setTextSize(40);
            button03.setTextSize(40);
            button04.setTextSize(40);
            button05.setTextSize(40);
            button06.setTextSize(40);
            button07.setTextSize(40);
            button08.setTextSize(40);
            button09.setTextSize(40);
            if (button01.getText().toString().equals("o"))
            {
                button01.setTextColor(Color.CYAN);
            }else
            {
                button01.setTextColor(Color.RED);
            }
            if (button02.getText().toString().equals("o"))
            {
                button02.setTextColor(Color.CYAN);
            }else
            {
                button02.setTextColor(Color.RED);
            }
            if (button03.getText().toString().equals("o"))
            {
                button03.setTextColor(Color.CYAN);
            }else
            {
                button03.setTextColor(Color.RED);
            }
            if (button04.getText().toString().equals("o"))
            {
                button04.setTextColor(Color.CYAN);
            }else
            {
                button04.setTextColor(Color.RED);
            }
            if (button05.getText().toString().equals("o"))
            {
                button05.setTextColor(Color.CYAN);
            }else
            {
                button05.setTextColor(Color.RED);
            }
            if (button06.getText().toString().equals("o"))
            {
                button06.setTextColor(Color.CYAN);
            }else
            {
                button06.setTextColor(Color.RED);
            }
            if (button07.getText().toString().equals("o"))
            {
                button07.setTextColor(Color.CYAN);
            }else
            {
                button07.setTextColor(Color.RED);
            }
            if (button08.getText().toString().equals("o"))
            {
                button08.setTextColor(Color.CYAN);
            }else
            {
                button08.setTextColor(Color.RED);
            }
            if (button09.getText().toString().equals("o"))
            {
                button09.setTextColor(Color.CYAN);
            }else
            {
                button09.setTextColor(Color.RED);
            }
        }
    }

    @Override
    public void onClick(View view) {
        opponentBG background = new opponentBG();
        gameEnd gameOver = new gameEnd();
        Log.d("player", "Player move");
//Determining which tile the player clicks, if it is empty, and placing an "x" or presenting a message
        if (player) {
            switch (view.getId()) {
                case R.id.button01:
                    if (!T11) {
                        button01.setText("x");
                        button01.setTextColor(Color.RED);
                        button01.setTextSize(40);
                        T11 = true;
                        player = false;
                        gameOver.execute();
                        break;
                    } else {
                        Toast.makeText(getBaseContext(), "Please choose an empty tile!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.button02:
                    if (!T12) {
                        button02.setText("x");
                        button02.setTextColor(Color.RED);
                        button02.setTextSize(40);
                        T12 = true;
                        player = false;
                        gameOver.execute();
                        break;
                    } else {
                        Toast.makeText(getBaseContext(), "Please choose an empty tile!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.button03:
                    if (!T13) {
                        button03.setText("x");
                        button03.setTextColor(Color.RED);
                        button03.setTextSize(40);
                        T13 = true;
                        player = false;
                        gameOver.execute();
                        break;
                    } else {
                        Toast.makeText(getBaseContext(), "Please choose an empty tile!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.button04:
                    if (!T21) {
                        button04.setText("x");
                        button04.setTextColor(Color.RED);
                        button04.setTextSize(40);
                        T21 = true;
                        player = false;
                        gameOver.execute();
                        break;
                    } else {
                        Toast.makeText(getBaseContext(), "Please choose an empty tile!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.button05:
                    if (!T22) {
                        button05.setText("x");
                        button05.setTextColor(Color.RED);
                        button05.setTextSize(40);
                        T22 = true;
                        player = false;
                        gameOver.execute();
                        break;
                    } else {
                        Toast.makeText(getBaseContext(), "Please choose an empty tile!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.button06:
                    if (!T23) {
                        button06.setText("x");
                        button06.setTextColor(Color.RED);
                        button06.setTextSize(40);
                        T23 = true;
                        player = false;
                        gameOver.execute();
                        break;
                    } else {
                        Toast.makeText(getBaseContext(), "Please choose an empty tile!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.button07:
                    if (!T31) {
                        button07.setText("x");
                        button07.setTextColor(Color.RED);
                        button07.setTextSize(40);
                        T31 = true;
                        player = false;
                        gameOver.execute();
                        break;
                    } else {
                        Toast.makeText(getBaseContext(), "Please choose an empty tile!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.button08:
                    if (!T32) {
                        button08.setText("x");
                        button08.setTextColor(Color.RED);
                        button08.setTextSize(40);
                        T32 = true;
                        player = false;
                        gameOver.execute();
                        break;
                    } else {
                        Toast.makeText(getBaseContext(), "Please choose an empty tile!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.button09:
                    if (!T33) {
                        button09.setText("x");
                        button09.setTextColor(Color.RED);
                        button09.setTextSize(40);
                        T33 = true;
                        player = false;
                        gameOver.execute();
                        break;
                    } else {
                        Toast.makeText(getBaseContext(), "Please choose an empty tile!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.button10:
                    clearBoard();
                    break;

                default:
                    break;
            }
        }
//Starting the task that controls Android's turn
        background.execute();
    }

//Task that checks if a wining move has occurred
    public class gameEnd extends AsyncTask<Integer, String, Integer> {

        String B11;
        String B12;
        String B13;
        String B21;
        String B22;
        String B23;
        String B31;
        String B32;
        String B33;
        int win;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//Retrieve the text of every button and assign it to a string representing each tile in manner B(ROW)(COLUMN)
            B11 = button01.getText().toString();
            B12 = button02.getText().toString();
            B13 = button03.getText().toString();
            B21 = button04.getText().toString();
            B22 = button05.getText().toString();
            B23 = button06.getText().toString();
            B31 = button07.getText().toString();
            B32 = button08.getText().toString();
            B33 = button09.getText().toString();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            Log.d("check", "Check if win");
//Checking every possible winning arrangement and who made it, returning 0 for Android win, 1 for player win, 2 for draw and 3 for "keep playing"
            if (B11.equals(B12) && B12.equals(B13)) {
                if (B11.equals("o")) {
                    win = 0;
                } else if (B11.equals("x")) {
                    win = 1;
                } else if (B11.equals("")) {
                    win = 3;
                }
            } else if (B21.equals(B22) && B22.equals(B23)) {
                if (B21.equals("o")) {
                    win = 0;
                } else if (B21.equals("x")) {
                    win = 1;
                } else if (B21.equals("")) {
                    win = 3;
                }
            } else if (B31.equals(B32) && B32.equals(B33)) {
                if (B31.equals("o")) {
                    win = 0;
                } else if (B31.equals("x")) {
                    win = 1;
                } else if (B31.equals("")) {
                    win = 3;
                }
            } else if (B11.equals(B21) && B21.equals(B31)) {
                if (B11.equals("o")) {
                    win = 0;
                } else if (B11.equals("x")) {
                    win = 1;
                } else if (B11.equals("")) {
                    win = 3;
                }
            } else if (B12.equals(B22) && B22.equals(B32)) {
                if (B12.equals("o")) {
                    win = 0;
                } else if (B12.equals("x")) {
                    win = 1;
                } else if (B12.equals("")) {
                    win = 3;
                }
            } else if (B13.equals(B23) && B23.equals(B33)) {
                if (B13.equals("o")) {
                    win = 0;
                } else if (B13.equals("x")) {
                    win = 1;
                } else if (B13.equals("")) {
                    win = 3;
                }
            } else if (B11.equals(B22) && B22.equals(B33)) {
                if (B11.equals("o")) {
                    win = 0;
                } else if (B11.equals("x")) {
                    win = 1;
                } else if (B11.equals("")) {
                    win = 3;
                }
            } else if (B13.equals(B22) && B22.equals(B31)) {
                if (B13.equals("o")) {
                    win = 0;
                } else if (B13.equals("x")) {
                    win = 1;
                } else if (B13.equals("")) {
                    win = 3;
                }
//If no win has been detected yet all tiles are filled, it returns 2 for draw. If not, returns 3 for "keep playing"
            } else if (T11 && T12 && T13 && T21 && T22 && T23 && T31 && T32 && T33) {
                win = 2;
            } else {
                win = 3;
            }
            return win;
        }

        @Override
        protected void onPostExecute(Integer answer) {
            super.onPostExecute(answer);
            Log.d("check", String.valueOf(win));
//Print a toast message with information about who won, clearing the board for a new game, and adding one point to whoever won.
            if (win == 0) {
                Toast.makeText(getBaseContext(), "SORRY, ANDROID WON!", Toast.LENGTH_SHORT).show();
                clearBoard();
                cWins++;
            } else if (win == 1) {
                Toast.makeText(getBaseContext(), "CONGRATULATIONS, YOU WON!", Toast.LENGTH_SHORT).show();
                clearBoard();
                pWins++;
            } else if (win == 2) {
                Toast.makeText(getBaseContext(), "THE GAME IS A DRAW!", Toast.LENGTH_SHORT).show();
                clearBoard();
                player = true;
            }
            playerScore.setText(PLAYERNAME + ": " + pWins);
            compScore.setText("Android: " + cWins);
        }
    }
//Task controlling Android's turn
    public class opponentBG extends AsyncTask<Integer, String, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
//Android's moves are randomized, each number 0-8 corresponds to one of the tiles
            Random rand = new Random();
            randNum = rand.nextInt(9);
            return randNum;
        }

        @Override
        protected void onPostExecute(Integer answer) {
            super.onPostExecute(answer);
            gameEnd gameOver = new gameEnd();
            Log.d("Opponent", "Opponent move");
//The random number generated in doInBackground decides which tile Android chooses
            while (!player) {
                switch (randNum) {
//Each tile has an assigned boolean value that is set to true once it has been used. If false, Android can place its "o" there.
                    case 0:
                        if (!T11) {
                            button01.setText("o");
                            button01.setTextColor(Color.CYAN);
                            button01.setTextSize(40);
                            T11 = true;
                            player = true;
                            break;
                        } else {
//If the boolean is true, i.e. it has already been used, the generated number is incremented to try the next tile, as there is no break in the else-statements
                            randNum++;
                        }

                    case 1:
                        if (!T12) {
                            button02.setText("o");
                            button02.setTextColor(Color.CYAN);
                            button02.setTextSize(40);
                            T12 = true;
                            player = true;
                            break;
                        } else {
                            randNum++;
                        }

                    case 2:
                        if (!T13) {
                            button03.setText("o");
                            button03.setTextColor(Color.CYAN);
                            button03.setTextSize(40);
                            T13 = true;
                            player = true;
                            break;
                        } else {
                            randNum++;
                        }

                    case 3:
                        if (!T21) {
                            button04.setText("o");
                            button04.setTextColor(Color.CYAN);
                            button04.setTextSize(40);
                            T21 = true;
                            player = true;
                            break;
                        } else {
                            randNum++;
                        }

                    case 4:
                        if (!T22) {
                            button05.setText("o");
                            button05.setTextColor(Color.CYAN);
                            button05.setTextSize(40);
                            T22 = true;
                            player = true;
                            break;
                        } else {
                            randNum++;
                        }

                    case 5:
                        if (!T23) {
                            button06.setText("o");
                            button06.setTextColor(Color.CYAN);
                            button06.setTextSize(40);
                            T23 = true;
                            player = true;
                            break;
                        } else {
                            randNum++;
                        }

                    case 6:
                        if (!T31) {
                            button07.setText("o");
                            button07.setTextColor(Color.CYAN);
                            button07.setTextSize(40);
                            T31 = true;
                            player = true;
                            break;
                        } else {
                            randNum++;
                        }

                    case 7:
                        if (!T32) {
                            button08.setText("o");
                            button08.setTextColor(Color.CYAN);
                            button08.setTextSize(40);
                            T32 = true;
                            player = true;
                            break;
                        } else {
                            randNum++;
                        }

                    case 8:
                        if (!T33) {
                            button09.setText("o");
                            button09.setTextColor(Color.CYAN);
                            button09.setTextSize(40);
                            T33 = true;
                            player = true;
                            break;
                        } else {
//Here we cannot increment the random number since there is no case 9, so it is set back to 0 to be run through again
                            randNum = 0;
                        }

                    default:
                        break;
                }
            }
//Checking if a winning move has been made
            gameOver.execute();
        }
    }
//Used to clear the tiles and all associated booleans when called, resetting the board
    public void clearBoard() {
        button01.setText("");
        button02.setText("");
        button03.setText("");
        button04.setText("");
        button05.setText("");
        button06.setText("");
        button07.setText("");
        button08.setText("");
        button09.setText("");

        T11 = false;
        T12 = false;
        T13 = false;
        T21 = false;
        T22 = false;
        T23 = false;
        T31 = false;
        T32 = false;
        T33 = false;
        player = true;
    }
//Saving the placement of "x" or "o" as well as points onPause
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("B11", button01.getText().toString());
        savedInstanceState.putString("B12", button02.getText().toString());
        savedInstanceState.putString("B13", button03.getText().toString());
        savedInstanceState.putString("B21", button04.getText().toString());
        savedInstanceState.putString("B22", button05.getText().toString());
        savedInstanceState.putString("B23", button06.getText().toString());
        savedInstanceState.putString("B31", button07.getText().toString());
        savedInstanceState.putString("B32", button08.getText().toString());
        savedInstanceState.putString("B33", button09.getText().toString());
        savedInstanceState.putInt("Player", cWins);
        savedInstanceState.putInt("Comp", pWins);
    }
//Used when activity is stopped to save the important data
    @Override
    protected void onStop() {
        super.onStop();
        GregorianCalendar lastPlay = new GregorianCalendar();
        SharedPreferences prefsScore = getSharedPreferences("SCORES", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefsScore.edit();
//Adding scores from current session to existing saved scores, if any
        cWins += cWins2;
        pWins += pWins2;
//Getting the time the last game ended and saving it in SharedPreferences, with the scores
        editor.putLong("TIME", lastPlay.getTimeInMillis());
        Log.d("Time",  String.valueOf(lastPlay.getTimeInMillis()));
        editor.putInt("ANDROID", cWins);
        editor.putInt(PLAYERNAME, pWins);

        editor.commit();
    }
}