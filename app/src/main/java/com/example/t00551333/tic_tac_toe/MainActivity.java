package com.example.t00551333.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
//Main Menu activity, from which user chooses where to go
public class MainActivity extends AppCompatActivity {

    ListView optionsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Adding options to listView
        optionsView = (ListView)findViewById(R.id.optionslist);
        List<String> optionsList = new ArrayList<>();
        optionsList.add("Enter Name");
        optionsList.add("Play a Game");
        optionsList.add("Show Score Tally");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, optionsList);
        optionsView.setAdapter(adapter);
//Setting the list elements to start their respective activities
        optionsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(MainActivity.this, EnterNamesActivity.class);
                    startActivity(intent);
                }
                else if (i == 1) {
                    Intent intent = new Intent(MainActivity.this, PlayGameActivity.class);
                    startActivity(intent);
                }
                else if (i == 2) {
                    Intent intent = new Intent(MainActivity.this, ShowStandingsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
