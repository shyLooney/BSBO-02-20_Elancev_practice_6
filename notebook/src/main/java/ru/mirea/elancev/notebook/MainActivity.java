package ru.mirea.elancev.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import kotlin.text.Charsets;

public class MainActivity extends AppCompatActivity {
    EditText text;
    EditText fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.editTextTextMultiLine);
        fileName = findViewById(R.id.editFileName);
    }

    public void readFile(View view) {
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName.getText().toString());
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            for (int i = 0; i < lines.size(); i++) {
                text.setText(text.getText() + lines.get(i));
            }
        } catch (Exception e) {
            Log.w("ExternalStorage", String.format("Read from file %s failed", e.getMessage()));
        }
    }

    public void writeFile(View view) {
            String string = text.getText().toString();
            File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS);
            File file = new File(path, fileName.getText().toString());
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
                OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
                output.write(string);
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}