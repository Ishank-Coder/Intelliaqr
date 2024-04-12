package com.example.intelliqr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.time.Clock;

public class MainActivity extends AppCompatActivity {
    Button scanbtn;
    Button plannerbtn;
    Button socialbtn;

    @SuppressLint("StaticFieldLeak")
    static TextClock textClock;
    @SuppressLint("StaticFieldLeak")
    public static TextView scantext;
    @SuppressLint("StaticFieldLeak")
    static ImageView tickImageView;
    @SuppressLint("StaticFieldLeak")
    static ImageView crossImageView;
    @SuppressLint("StaticFieldLeak")
    static ImageView cautionImageView;
    @SuppressLint("StaticFieldLeak")
    static ImageView loading;

    @SuppressLint({"CutPasteId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scantext = findViewById(R.id.scantext);
        scanbtn = findViewById(R.id.scanbtn);

        textClock = findViewById(R.id.textClock);

        tickImageView = findViewById(R.id.tickImageView);
        crossImageView = findViewById(R.id.crossImageView);
        cautionImageView = findViewById(R.id.cautionImageView);

        plannerbtn = findViewById(R.id.plannerbtn);
        socialbtn = findViewById(R.id.socialbtn);

        loading = findViewById(R.id.loading);



        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), scannerView.class));
            }
        });

        textClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), plannerBtn.class));
            }
        });

        plannerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), plannerBtn.class));
            }
        });

        socialbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), socialView.class));
            }
        });

        tickImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(), scannerView.class));
            }
        });

        crossImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(), scannerView.class));
            }
        });

        cautionImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(), scannerView.class));
            }
        });
    }
}
