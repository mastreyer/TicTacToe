package com.example.t00551333.tic_tac_toe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Activity where user enters their name
public class EnterNamesActivity extends MainActivity implements View.OnClickListener {
    Button saveButton;
    EditText nameInput;
    String NAME;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_names);

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        nameInput = (EditText) findViewById(R.id.editText);
    }

    @Override
    public void onClick(View v) {
//Assigning the name entered by the player to s string and displaying a toast message, then killing the activity, forcing onStop
        NAME = nameInput.getText().toString();
        Toast.makeText(getBaseContext(), "Name saved!", Toast.LENGTH_SHORT).show();
        nameInput.setText("");
        this.finish();
    }
//Saves the entered name in SharedPreferences twice onStop, once to keep it permanently, and once to make it the "current player"
    @Override
    protected void onStop() {
        super.onStop();
            SharedPreferences prefsName = getSharedPreferences("NAMES", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefsName.edit();
            editor.putString(NAME, NAME);
            editor.putString("MostRecent", NAME);

            editor.commit();
    }
}
