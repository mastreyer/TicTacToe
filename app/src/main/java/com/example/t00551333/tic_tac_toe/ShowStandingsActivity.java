package com.example.t00551333.tic_tac_toe;

import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
//Activity showing scores of Android and up to 3 different players, as well as time of last game
public class ShowStandingsActivity extends MainActivity  {

    int cWins = 0, pWins = 0;
    String PLAYERNAME, PLACEHOLDER1, PLACEHOLDER2, PLACEHOLDER3;
    TextView scoreBoard, scoreBoard2, scoreBoard3, scoreBoard4, scoreBoard5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_standings);
//Retrieves scores, player names and saved textView texts
        SharedPreferences prefsName = this.getSharedPreferences("NAMES", MODE_PRIVATE);
        SharedPreferences prefsScore = this.getSharedPreferences("SCORES", MODE_PRIVATE);
        SharedPreferences prefsText = this.getSharedPreferences("TEXTS", MODE_PRIVATE);

        cWins = prefsScore.getInt("ANDROID", cWins);
        PLAYERNAME = prefsName.getString("MostRecent", PLAYERNAME);
        pWins = prefsScore.getInt(PLAYERNAME, pWins);
        scoreBoard = (TextView) findViewById(R.id.textView4);
        scoreBoard2 = (TextView) findViewById(R.id.textView6);
        scoreBoard3 = (TextView) findViewById(R.id.textView5);
        scoreBoard4 = (TextView) findViewById(R.id.textView7);
        scoreBoard5 = (TextView) findViewById(R.id.textView8);
        Date date = new Date(prefsScore.getLong("TIME", 0));
//Only shows up if this activity is started without having ever entered a name or started a game
        if (PLAYERNAME == null || PLAYERNAME == "") {
            scoreBoard.setText("No scores to show yet!");
        }else{
//Gets the saved values of the textViews
            PLACEHOLDER1 = prefsText.getString("scoreBoard3", PLACEHOLDER1);
            PLACEHOLDER2 = prefsText.getString("scoreBoard4", PLACEHOLDER2);
            PLACEHOLDER3 = prefsText.getString("scoreBoard5", PLACEHOLDER3);
//Sets the saved values to the textViews
            scoreBoard3.setText(PLACEHOLDER1);
            scoreBoard4.setText(PLACEHOLDER2);
            scoreBoard5.setText(PLACEHOLDER3);
            Log.d("Scoreboard3", scoreBoard3.getText().toString());
//Sets the time of last game as well as Android's entry on the scoreboard
            scoreBoard.setText("Last played: " + date);
            scoreBoard2.setText("Android: " + cWins);
//If statements ensure that the same name isn't added to the scoreboard more than once
            if (scoreBoard3.getText().toString().contains(PLAYERNAME) || scoreBoard3.getText().toString().equals(""))
                scoreBoard3.setText(PLAYERNAME + ": " + pWins);
            else if (scoreBoard4.getText().toString().contains(PLAYERNAME) || scoreBoard4.getText().toString().equals(""))
                scoreBoard4.setText(PLAYERNAME + ": " + pWins);
            else if (scoreBoard5.getText().toString().contains(PLAYERNAME) || scoreBoard5.getText().toString().equals(""))
                scoreBoard5.setText(PLAYERNAME + ": " + pWins);
        }
    }
//Saving the scoreboard texts onPause
    public void onPause() {
        super.onPause();

        SharedPreferences settings = getSharedPreferences("TEXTS", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("scoreBoard3", scoreBoard3.getText().toString());
        editor.putString("scoreBoard4", scoreBoard4.getText().toString());
        editor.putString("scoreBoard5", scoreBoard5.getText().toString());

        editor.commit();
        Log.d("Scoreboard3", scoreBoard3.getText().toString());
    }
    //Saving the scoreboard texts onStop
    public void onStop() {
        super.onStop();

        SharedPreferences prefsText = getSharedPreferences("TEXTS", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefsText.edit();

        PLACEHOLDER1 = scoreBoard3.getText().toString();
        PLACEHOLDER2 = scoreBoard4.getText().toString();
        PLACEHOLDER3 = scoreBoard5.getText().toString();

        editor.putString("scoreBoard3", PLACEHOLDER1);
        editor.putString("scoreBoard4", PLACEHOLDER2);
        editor.putString("scoreBoard5", PLACEHOLDER3);
        editor.commit();
        Log.d("Scoreboard3", scoreBoard3.getText().toString());
    }
}
