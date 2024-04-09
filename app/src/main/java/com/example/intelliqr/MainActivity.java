package com.example.intelliqr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button scanbtn;
    @SuppressLint("StaticFieldLeak")
    public static TextView scantext;
    @SuppressLint("StaticFieldLeak")
    static ImageView tickImageView;
    @SuppressLint("StaticFieldLeak")
    static ImageView crossImageView;
    @SuppressLint("StaticFieldLeak")
    static ImageView cautionImageView;
    @SuppressLint("StaticFieldLeak")
    static ImageView instagram;
    @SuppressLint("StaticFieldLeak")
    static ImageView linkedin;
    @SuppressLint("StaticFieldLeak")
    static ImageView whatsapp;
    @SuppressLint("StaticFieldLeak")
    static ImageView twitter;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scantext = findViewById(R.id.scantext);
        scanbtn = findViewById(R.id.scanbtn);

        tickImageView = findViewById(R.id.tickImageView);
        crossImageView = findViewById(R.id.crossImageView);
        cautionImageView = findViewById(R.id.cautionImageView);

        instagram = findViewById(R.id.instagram);
        linkedin = findViewById(R.id.linkedin);
        whatsapp = findViewById(R.id.whatsapp);
        twitter = findViewById(R.id.twitter);

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), scannerView.class));
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

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a link when the image is clicked
                String url = "https://www.instagram.com/intellia_miet/"; // Your URL here
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a link when the image is clicked
                String url = "https://www.linkedin.com/company/intellia-society/"; // Your URL here
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a link when the image is clicked
                String url = "https://chat.whatsapp.com/Lkhkp7Wx7PiHa1ToEZyLwL"; // Your URL here
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a link when the image is clicked
                String url = "https://twitter.com/IntelliaSociety"; // Your URL here
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
