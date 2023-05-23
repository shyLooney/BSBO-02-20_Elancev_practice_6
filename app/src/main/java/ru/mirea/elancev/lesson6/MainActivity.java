package ru.mirea.elancev.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        editText1 = findViewById(R.id.editTextTextPersonName3);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        editText3 = findViewById(R.id.editTextTextPersonName);
    }

    public void onClick(View view)
    {
        editor.putString("GROUP", editText1.getText().toString());
        editor.putString("NUMBER", editText2.getText().toString());
        editor.putString("FILM", editText3.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String group = sharedPref.getString("GROUP", "unknown");
        String number = sharedPref.getString("NUMBER", "unknown");
        String film = sharedPref.getString("FILM", "unknown");
        editText1.setText(group);
        editText2.setText(number);
        editText3.setText(film);
    }
}