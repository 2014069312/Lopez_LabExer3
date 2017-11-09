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
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    Button btnLoadSharedPreferences, btnLoadInternalStorage, btnLoadInternalCache, btnLoadExternalCache, btnLoadExternalStorage,
            btnLoadExternalPublic, btnBack;
    TextView tvDisplay;
    FileInputStream fis;
    SharedPreferences preferences;
    EditText etFilename2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnLoadSharedPreferences = (Button) findViewById(R.id.btn_loadSharedPreferences);
        btnLoadInternalStorage = (Button) findViewById(R.id.btn_loadInternalStorage);
        btnLoadInternalCache = (Button) findViewById(R.id.btn_loadInternalCache);
        btnLoadExternalCache = (Button) findViewById(R.id.btn_loadExternalCache);
        btnLoadExternalStorage = (Button) findViewById(R.id.btn_loadExternalStorage);
        btnLoadExternalPublic = (Button) findViewById(R.id.btn_loadExternalPublic);
        btnBack = (Button) findViewById(R.id.btn_back);
        etFilename2 = (EditText) findViewById(R.id.et_filename2);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
        preferences = getSharedPreferences("pref",Context.MODE_WORLD_READABLE);
    }

    public void loadSharedPreferences (View view) {
        String data = preferences.getString("data", "");

        tvDisplay.setText(data);
    }

    public void loadInternalStorage (View view) {
        String filename = etFilename2.getText().toString();

        StringBuffer buffer = new StringBuffer();

        int read = 0;
        try {
            fis = openFileInput(filename);
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            } fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void loadInternalCache(View views){
        String filename = etFilename2.getText().toString();

        StringBuffer buffer = new StringBuffer();

        int read = 0;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(new File(getCacheDir(),filename));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void loadExternalCache(View view){
        String filename = etFilename2.getText().toString();

        StringBuffer buffer = new StringBuffer();

        int read = 0;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(new File(getExternalCacheDir(), filename));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void loadExternalStorage(View view){
        String filename = etFilename2.getText().toString();

        StringBuffer buffer = new StringBuffer();

        int read = 0;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(new File(getExternalFilesDir("temp"),filename));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void loadExternalPublicStorage(View view){
        String filename = etFilename2.getText().toString();

        StringBuffer buffer = new StringBuffer();

        int read = 0;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),filename));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void goBack (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
