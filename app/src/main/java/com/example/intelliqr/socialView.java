package com.example.intelliqr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class socialView extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static ImageView instaimg;
    @SuppressLint("StaticFieldLeak")
    static ImageView instaqr;
    @SuppressLint("StaticFieldLeak")
    static ImageView linkedinimg;
    @SuppressLint("StaticFieldLeak")
    static ImageView linkedinqr;
    @SuppressLint("StaticFieldLeak")
    static ImageView intelliaimg;
    @SuppressLint("StaticFieldLeak")
    static ImageView intelliawebqr;
    @SuppressLint("StaticFieldLeak")
    static ImageView whatsappimg;
    @SuppressLint("StaticFieldLeak")
    static ImageView whatsappqr;
    @SuppressLint("StaticFieldLeak")
    static ImageView twitterimg;
    @SuppressLint("StaticFieldLeak")
    static ImageView twitterqr;

    Button twitter;
    Button linkedin;
    Button instagram;
    Button intelliawebsite;
    Button whatsapp;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_social);

        instaqr = findViewById(R.id.instaqr);
        instaimg = findViewById(R.id.instaimg);

        intelliaimg = findViewById(R.id.intelliaimg);
        intelliawebqr = findViewById(R.id.intelliawebqr);

        linkedinimg = findViewById(R.id.linkedinimg);
        linkedinqr = findViewById(R.id.linkedinqr);

        whatsappimg = findViewById(R.id.whatsappimg);
        whatsappqr = findViewById(R.id.whatsappqr);

        twitterimg = findViewById(R.id.twitterimg);
        twitterqr = findViewById(R.id.twitterqr);

        twitter = findViewById(R.id.twitter);
        instagram = findViewById(R.id.instagram);
        linkedin = findViewById(R.id.linkedin);
        intelliawebsite = findViewById(R.id.intelliawebsite);
        whatsapp = findViewById(R.id.whatsapp);

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialView.intelliawebqr.setVisibility(View.GONE);
                socialView.whatsappqr.setVisibility(View.GONE);
                socialView.twitterqr.setVisibility(View.GONE);
                socialView.linkedinqr.setVisibility(View.GONE);
                socialView.instaqr.setVisibility(View.VISIBLE);
            }
        });

        intelliawebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialView.instaqr.setVisibility(View.GONE);
                socialView.whatsappqr.setVisibility(View.GONE);
                socialView.twitterqr.setVisibility(View.GONE);
                socialView.linkedinqr.setVisibility(View.GONE);
                socialView.intelliawebqr.setVisibility(View.VISIBLE);
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                socialView.instaqr.setVisibility(View.GONE);
                socialView.intelliawebqr.setVisibility(View.GONE);
                socialView.twitterqr.setVisibility(View.GONE);
                socialView.linkedinqr.setVisibility(View.GONE);
                socialView.whatsappqr.setVisibility(View.VISIBLE);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialView.instaqr.setVisibility(View.GONE);
                socialView.intelliawebqr.setVisibility(View.GONE);
                socialView.whatsappqr.setVisibility(View.GONE);
                socialView.linkedinqr.setVisibility(View.GONE);
                socialView.twitterqr.setVisibility(View.VISIBLE);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
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
