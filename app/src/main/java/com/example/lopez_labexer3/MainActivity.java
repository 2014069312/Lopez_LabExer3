package com.example.lopez_labexer3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etData, etFilename;
    Button btnSharedPreferences, btnInternalStorage, btnInternalCache, btnExternalCache, btnExternalStorage, btnExternalPublic, btnNext;
    SharedPreferences preferences;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etData = (EditText) findViewById(R.id.et_data);
        etFilename = (EditText) findViewById(R.id.et_filename);
        btnSharedPreferences = (Button) findViewById(R.id.btn_sharedPreferences);
        btnInternalStorage = (Button) findViewById(R.id.btn_internalStorage);
        btnInternalCache = (Button) findViewById(R.id.btn_internalCache);
        btnExternalCache = (Button) findViewById(R.id.btn_externalCache);
        btnExternalStorage = (Button) findViewById(R.id.btn_externalStorage);
        btnExternalPublic = (Button) findViewById(R.id.btn_externalPublic);
        btnNext = (Button) findViewById(R.id.btn_next);
        preferences = getSharedPreferences("pref", Context.MODE_WORLD_READABLE);
    }

    public void saveSharedPreference (View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data", etData.getText().toString());
        editor.commit();

        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalStorage (View view) {
        String data = etData.getText().toString();
        String path = etFilename.getText().toString();
        try {
            fos = openFileOutput(path, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalCache(View view){
        String data = etData.getText().toString();
        String path = etFilename.getText().toString();

        File folder = getCacheDir();
        File file = new File(folder, path);
        writeData(file,data);

        Toast.makeText(this,"Saved to Internal Cache!",Toast.LENGTH_SHORT).show();
    }

    public void saveExternalCache(View view){
        String data = etData.getText().toString();
        String path = etFilename.getText().toString();

        File folder = getExternalCacheDir();
        File file = new File(folder, path);

        writeData(file,data);
        Toast.makeText(this,"Saved to External Cache!",Toast.LENGTH_SHORT).show();
    }

    public void saveExternalStorage(View view){
        String data = etData.getText().toString();
        String path = etFilename.getText().toString();

        File folder = getExternalFilesDir("temp");
        File file = new File(folder, path);

        writeData(file,data);
        Toast.makeText(this,"Saved in External Storage!",Toast.LENGTH_SHORT).show();
    }

    public void saveExternalPublicStorage(View view){
        String data = etData.getText().toString();
        String path = etFilename.getText().toString();

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, path);

        writeData(file,data);
        Toast.makeText(this,"Saved to External Public Storage!",Toast.LENGTH_SHORT).show();
    }

    public void writeData(File file, String message){

        try{
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void goToNext (View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
