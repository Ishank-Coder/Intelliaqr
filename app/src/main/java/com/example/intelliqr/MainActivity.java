package com.example.intelliqr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {
    Button scanbtn;
    Button plannerbtn;
    Button socialbtn;
    Button mcount;
    Switch mealSwitch;

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
    @SuppressLint("StaticFieldLeak")
    public static TextView currentact;
    @SuppressLint("StaticFieldLeak")
    public static TextView upcomingact;

    @SuppressLint({"CutPasteId", "MissingInflatedId", "SetTextI18n"})
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

        currentact = findViewById(R.id.currentact);
        upcomingact = findViewById(R.id.upcomingact);

        mcount = findViewById(R.id.mcount);

        mealSwitch = findViewById(R.id.mealswitch);

        // Set initial state
        scanbtn.setEnabled(mealSwitch.isChecked());

        // Add switch state change listener
        mealSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            scanbtn.setEnabled(isChecked);
        });


        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), scannerView.class));
            }
        });

        plannerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), plannerBtn.class));
            }
        });

        mcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), mcountView.class));
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


        //this the section for the timeline on the home screen
        LocalDate today = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            today = LocalDate.now();
            LocalDate date1 = LocalDate.parse("2024-04-17");  // Parse from a String

            if (today.equals(date1)) {
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime start = date1.atTime(8,59,59);

                // if-else ladder to check which event is going on currently and display it on the timeline accordingly
                //check in
                if (currentTime.isAfter(start) && currentTime.isBefore(start.plusHours(1))){
                    MainActivity.currentact.setText("Check in");
                    MainActivity.upcomingact.setText("Upcoming: Welcome Speech (10:00 A.M)");
                }

                //welcome speech
                else if (currentTime.isAfter(start.plusHours(1)) && currentTime.isBefore(start.plusHours(1).plusMinutes(15))) {
                    MainActivity.currentact.setText("Welcome Speech");
                    MainActivity.upcomingact.setText("Upcoming: Lamp Lighting (10:15 A.M)");
                }

                //lamp lighting
                else if (currentTime.isAfter(start.plusHours(1).plusMinutes(15)) && currentTime.isBefore(start.plusHours(1).plusMinutes(30))) {
                    MainActivity.currentact.setText("Lamp Lighting");
                    MainActivity.upcomingact.setText("Upcoming: Event Explanation (10:30 A.M)");
                }

                //event explanation
                else if (currentTime.isAfter(start.plusHours(1).plusMinutes(30)) && currentTime.isBefore(start.plusHours(2))) {
                    MainActivity.currentact.setText("Event Explanation");
                    MainActivity.upcomingact.setText("Upcoming: Sponsor Speech 1 (11:00 A.M)");
                }

                //Sponsor Speech 1
                else if (currentTime.isAfter(start.plusHours(2)) && currentTime.isBefore(start.plusHours(2).plusMinutes(30))) {
                    MainActivity.currentact.setText("Sponsor Speech 1");
                    MainActivity.upcomingact.setText("Upcoming: Design Thinking by ACIC (11:30 A.M)");
                }

                //Design thinking by ACIC
                else if (currentTime.isAfter(start.plusHours(2).plusMinutes(30)) && currentTime.isBefore(start.plusHours(4))) {
                    MainActivity.currentact.setText("Design Thinking by ACIC");
                    MainActivity.upcomingact.setText("Upcoming: Lunch (1:00 P.M)");
                }

                //LUNCH
                else if (currentTime.isAfter(start.plusHours(4)) && currentTime.isBefore(start.plusHours(5))) {
                    MainActivity.currentact.setText("Lunch");
                    MainActivity.upcomingact.setText("Upcoming: PPT Development Begins (2:00 P.M)");
                }

                //PPT development begins
                else if (currentTime.isAfter(start.plusHours(5)) && currentTime.isBefore(start.plusHours(5).plusMinutes(30))) {
                    MainActivity.currentact.setText("PPT Development Begins");
                    MainActivity.upcomingact.setText("Upcoming: Sponsor Speech 2 (2:30 P.M)");
                }

                //sponsor speech 2
                else if (currentTime.isAfter(start.plusHours(5).plusMinutes(30)) && currentTime.isBefore(start.plusHours(6))) {
                    MainActivity.currentact.setText("Sponsor Speech 2");
                    MainActivity.upcomingact.setText("Upcoming: Fun Activity 1 (3:00 P.M)");
                }

                //fun activity 1
                else if (currentTime.isAfter(start.plusHours(6)) && currentTime.isBefore(start.plusHours(7))) {
                    MainActivity.currentact.setText("Fun Activity 1");
                    MainActivity.upcomingact.setText("Upcoming: Evaluation (4:00 P.M)");
                }

                //Evaluation
                else if (currentTime.isAfter(start.plusHours(7)) && currentTime.isBefore(start.plusHours(7).plusMinutes(30))) {
                    MainActivity.currentact.setText("Evaluation");
                    MainActivity.upcomingact.setText("Upcoming: Results and Elimination (4:30 P.M)");
                }

                //Result and elimination
                else if (currentTime.isAfter(start.plusHours(7).plusMinutes(30)) && currentTime.isBefore(start.plusHours(8))) {
                    MainActivity.currentact.setText("Results and Elimination");
                    MainActivity.upcomingact.setText("Upcoming: Evening Snacks (5:00 P.M)");
                }

                //Evening Snacks
                else if (currentTime.isAfter(start.plusHours(8)) && currentTime.isBefore(start.plusHours(8).plusMinutes(45))) {
                    MainActivity.currentact.setText("Evening Snacks");
                    MainActivity.upcomingact.setText("Upcoming: Event Explanation 2 (5:45 P.M)");
                }

                //Event Explanation 2
                else if (currentTime.isAfter(start.plusHours(8).plusMinutes(45)) && currentTime.isBefore(start.plusHours(9))) {
                    MainActivity.currentact.setText("Event Explanation 2");
                    MainActivity.upcomingact.setText("Upcoming: Development Slot 1 Begins (6:00 P.M)");
                }

                //Development slot 1 Begins
                else if (currentTime.isAfter(start.plusHours(9)) && currentTime.isBefore(start.plusHours(10))) {
                    MainActivity.currentact.setText("Development Slot 1 Begins");
                    MainActivity.upcomingact.setText("Upcoming: Swag Sale (7:00 P.M)");
                }

                //Swag sale
                else if (currentTime.isAfter(start.plusHours(10)) && currentTime.isBefore(start.plusHours(12))) {
                    MainActivity.currentact.setText("Swag Sale");
                    MainActivity.upcomingact.setText("Upcoming: Dinner (9:00 P.M)");
                }

                //DINNER
                else if (currentTime.isAfter(start.plusHours(12)) && currentTime.isBefore(start.plusHours(13))) {
                    MainActivity.currentact.setText("Dinner");
                    MainActivity.upcomingact.setText("Upcoming: Development Slot 2 Begins (10:00 P.M)");
                }

                //Development Slot 2 Begins
                else if (currentTime.isAfter(start.plusHours(13)) && currentTime.isBefore(start.plusHours(14))) {
                    MainActivity.currentact.setText("Development Slot 2 Begins");
                    MainActivity.upcomingact.setText("Upcoming: Fun Activity 2 (11:00 P.M)");
                }

                //fun activity 2
                else if (currentTime.isAfter(start.plusHours(14)) && currentTime.isBefore(start.plusHours(14).plusMinutes(30))) {
                    MainActivity.currentact.setText("Fun Activity 2");
                    MainActivity.upcomingact.setText("Upcoming: Mentoring Session (11:30 P.M)");
                }

                //mentoring session
                else if (currentTime.isAfter(start.plusHours(14).plusMinutes(30)) && currentTime.isBefore(start.plusHours(16))) {
                    MainActivity.currentact.setText("Mentoring Session");
                    MainActivity.upcomingact.setText("Upcoming: Fun Fare (pitch perfect) (1:00 A.M)");
                }

                //Fun Fare (pitch perfect)
                else if (currentTime.isAfter(start.plusHours(16)) && currentTime.isBefore(start.plusHours(18))) {
                    MainActivity.currentact.setText("Fun Fare (pitch perfect)");
                    MainActivity.upcomingact.setText("Upcoming: Development Slot 3 Begins (3:00 A.M)");
                }

                //Development slot 3 begins
                else if (currentTime.isAfter(start.plusHours(18)) && currentTime.isBefore(start.plusHours(19))) {
                    MainActivity.currentact.setText("Development Slot 3 Begins");
                    MainActivity.upcomingact.setText("Upcoming: Sponsor Speech 5 (4:00 A.M)");
                }

                //sponsor speech 5
                else if (currentTime.isAfter(start.plusHours(19)) && currentTime.isBefore(start.plusHours(19).plusMinutes(30))) {
                    MainActivity.currentact.setText("Sponsor Speech 5");
                    MainActivity.upcomingact.setText("Upcoming: Open mic for Society Members (4:30 A.M)");
                }

                //Open mic for society members
                else if (currentTime.isAfter(start.plusHours(19).plusMinutes(30)) && currentTime.isBefore(start.plusHours(21))) {
                    MainActivity.currentact.setText("Open mic for Society Members");
                    MainActivity.upcomingact.setText("Upcoming: Elimination (6:00 A.M)");
                }

                //Elimination
                else if (currentTime.isAfter(start.plusHours(21)) && currentTime.isBefore(start.plusHours(21).plusMinutes(30))) {
                    MainActivity.currentact.setText("Elimination");
                    MainActivity.upcomingact.setText("Upcoming: Treasure Hunt (6:30 A.M)");
                }

                //Treasure Hunt
                else if (currentTime.isAfter(start.plusHours(21).plusMinutes(30)) && currentTime.isBefore(start.plusHours(22).plusMinutes(30))) {
                    MainActivity.currentact.setText("Treasure Hunt");
                    MainActivity.upcomingact.setText("Upcoming: Refreshments and Results(activity 2)(7:30 A.M)");
                }

                //Refreshments and results of activity 2
                else if (currentTime.isAfter(start.plusHours(22).plusMinutes(30)) && currentTime.isBefore(start.plusHours(23))) {
                    MainActivity.currentact.setText("Refreshments and Results(activity 2)");
                    MainActivity.upcomingact.setText("Upcoming: Pitching (8:00 A.M)");
                }

                //Pitching
                else if (currentTime.isAfter(start.plusHours(23)) && currentTime.isBefore(start.plusHours(25))) {
                    MainActivity.currentact.setText("Pitching");
                    MainActivity.upcomingact.setText("Upcoming: Breakfast (10:00 A.M)");
                }

                //BREAKFAST
                else if (currentTime.isAfter(start.plusHours(25)) && currentTime.isBefore(start.plusHours(26))) {
                    MainActivity.currentact.setText("Breakfast");
                    MainActivity.upcomingact.setText("Upcoming: Winners Announcement (11:00 A.M)");
                }

                //Winner Announcement and prizes
                else if (currentTime.isAfter(start.plusHours(26)) && currentTime.isBefore(start.plusHours(27))) {
                    MainActivity.currentact.setText("Winner Announcement");
                    MainActivity.upcomingact.setText("Upcoming: Pack Up");
                }



            }

            else if(today.isBefore(date1)){
                MainActivity.currentact.setText("No Current Activity");
                MainActivity.upcomingact.setText("Check in (20 April 2024,9:00 A.M)");
            }
        }
    }
}
