package ru.mirea.elancev.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.EditText;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);

            SharedPreferences secureSharedPreferences = EncryptedSharedPreferences.create(
                "secret_shared_prefs",
                mainKeyAlias,
                getBaseContext(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            secureSharedPreferences.edit().putString("secure", "Высоцкий Валоха").apply();
            EditText editText = findViewById(R.id.editTextTextPersonName);
            editText.setText(secureSharedPreferences.getString("secure", "4el"));
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}