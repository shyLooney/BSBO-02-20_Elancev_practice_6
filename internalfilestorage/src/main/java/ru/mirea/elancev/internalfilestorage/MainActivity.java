package ru.mirea.elancev.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String string = "1010 год\nОснование города Ярославля";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("myFile.txt", Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        String string = ((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString();
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("myFile.txt", Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}