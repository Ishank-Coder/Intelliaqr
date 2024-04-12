package com.example.intelliqr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class socialView extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static ImageView instalogo;
    @SuppressLint("StaticFieldLeak")
    static ImageView instaqr;
    @SuppressLint("StaticFieldLeak")
    static ImageView linkedinlogo;
    @SuppressLint("StaticFieldLeak")
    static ImageView linkedinqr;
    @SuppressLint("StaticFieldLeak")
    static ImageView intelliaWeblogo;
    @SuppressLint("StaticFieldLeak")
    static ImageView intelliawebqr;
    @SuppressLint("StaticFieldLeak")
    static ImageView whatsapplogo;
    @SuppressLint("StaticFieldLeak")
    static ImageView whatsappqr;
    @SuppressLint("StaticFieldLeak")
    static ImageView twitterlogo;
    @SuppressLint("StaticFieldLeak")
    static ImageView twitterqr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_social);

        instaqr = findViewById(R.id.instaqr);
        instalogo = findViewById(R.id.instalogo);

        intelliaWeblogo = findViewById(R.id.intelliaWeblogo);
        intelliawebqr = findViewById(R.id.intelliawebqr);

        linkedinlogo = findViewById(R.id.linkedinlogo);
        linkedinqr = findViewById(R.id.linkedinqr);

        whatsapplogo = findViewById(R.id.whatsapplogo);
        whatsappqr = findViewById(R.id.whatsappqr);

        twitterlogo = findViewById(R.id.twitterlogo);
        twitterqr = findViewById(R.id.twitterqr);

        instalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialView.intelliawebqr.setVisibility(View.GONE);
                socialView.whatsappqr.setVisibility(View.GONE);
                socialView.twitterqr.setVisibility(View.GONE);
                socialView.linkedinqr.setVisibility(View.GONE);
                socialView.instaqr.setVisibility(View.VISIBLE);
            }
        });

        intelliaWeblogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialView.instaqr.setVisibility(View.GONE);
                socialView.whatsappqr.setVisibility(View.GONE);
                socialView.twitterqr.setVisibility(View.GONE);
                socialView.linkedinqr.setVisibility(View.GONE);
                socialView.intelliawebqr.setVisibility(View.VISIBLE);
            }
        });

        whatsapplogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialView.instaqr.setVisibility(View.GONE);
                socialView.intelliawebqr.setVisibility(View.GONE);
                socialView.twitterqr.setVisibility(View.GONE);
                socialView.linkedinqr.setVisibility(View.GONE);
                socialView.whatsappqr.setVisibility(View.VISIBLE);
            }
        });

        twitterlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialView.instaqr.setVisibility(View.GONE);
                socialView.intelliawebqr.setVisibility(View.GONE);
                socialView.whatsappqr.setVisibility(View.GONE);
                socialView.linkedinqr.setVisibility(View.GONE);
                socialView.twitterqr.setVisibility(View.VISIBLE);
            }
        });

        linkedinlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialView.instaqr.setVisibility(View.GONE);
                socialView.intelliawebqr.setVisibility(View.GONE);
                socialView.whatsappqr.setVisibility(View.GONE);
                socialView.twitterqr.setVisibility(View.GONE);
                socialView.linkedinqr.setVisibility(View.VISIBLE);
            }
        });
    }
}
