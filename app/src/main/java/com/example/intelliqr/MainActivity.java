package com.example.intelliqr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    }
}
