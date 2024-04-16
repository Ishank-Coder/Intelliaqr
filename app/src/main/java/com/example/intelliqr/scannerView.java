package com.example.intelliqr;

import java.util.Base64;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalTime; // import the LocalTime class
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.Manifest;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

// This activity handles QR scanning
public class scannerView extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView; // Declaring ZXingScannerView object
    DatabaseReference dbref;// Firebase database reference object
    DatabaseReference dbref1; //Firebase database reference object2
    String m1 = "Meal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this); // Initializing ZXingScannerView
        setContentView(scannerView); // Setting the content view to ZXingScannerView
        dbref = FirebaseDatabase.getInstance().getReference().child("hackathon").child("participants"); // Getting reference to Firebase database
        dbref1 = FirebaseDatabase.getInstance().getReference().child("hackathon").child("invalid QRs");
        // Requesting camera permission using Dexter library
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera(); // Starting camera when permission is granted
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        // Handling denied permission
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest(); // Continuing permission request if rationale should be shown
                    }
                }).check(); // Checking permission
    }

    // Handling the result of QR code scanning
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void handleResult(Result rawResult) {
        final String participantID = rawResult.getText();
        String encodedUrl = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedUrl = Base64.getEncoder().encodeToString(participantID.getBytes());
        }

        LocalDate today = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            today = LocalDate.now();
            LocalDate date1 = LocalDate.parse("2024-04-17");  // Parse from a String
            LocalDate date2 = LocalDate.parse("2024-04-18");    // parse from a string


            if (today.equals(date1)) {
                LocalTime currentTime = LocalTime.now();

                LocalTime Afternoon = LocalTime.of(12, 30);
                LocalTime afternoon = LocalTime.of(14, 30);

                LocalTime Evening = LocalTime.of(16, 30);
                LocalTime evening = LocalTime.of(18, 15);

                LocalTime Night = LocalTime.of(20, 30);
                LocalTime night = LocalTime.of(22, 30);

                if (currentTime.isAfter(Evening) & (currentTime.isBefore(evening))) {
                    m1 = "Evening Snack";
                } else if (currentTime.isAfter(Afternoon) & (currentTime.isBefore(afternoon))) {
                    m1 = "Lunch";
                } else if (currentTime.isAfter(Night) & (currentTime.isBefore(night))) {
                    m1 = "Dinner";
                }


            } else if (today.equals(date2)) {
                LocalTime currentTime = LocalTime.now();
                LocalTime Morning = LocalTime.of(7, 30);
                LocalTime morning = LocalTime.of(9, 30);

                LocalTime LateNight = LocalTime.of(0, 30);
                LocalTime lateNight = LocalTime.of(2, 30);

                if (currentTime.isAfter(Morning) & (currentTime.isBefore(morning))) {
                    m1 = "Breakfast";
                } else if (currentTime.isAfter(LateNight) & (currentTime.isBefore(lateNight))) {
                    m1 = "Late Night Snack";
                }
            }
        }

        int i;
        String[] array = new String[192];
        for (i = 1; i <= 191; i++) {
            array[i] = String.format("%03d", i); // Storing even numbers, you can change this to any sequence you want
        }
        boolean test = Arrays.asList(array).contains(participantID);

        if (test==true) {
            // Assuming participantID is the unique identifier for each participant
            dbref.child(participantID).child(m1).addListenerForSingleValueEvent(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Check if the dataSnapshot exists and contains a boolean value
                    if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                        boolean existingValue = (boolean) dataSnapshot.getValue();
                        // Now you have the existing value stored under the child "m1"
                        if (existingValue) {
                            // The value is already true, indicating it has been recorded before
                            if (Objects.equals(m1, "Meal")) {
                                MainActivity.scantext.setText("Participant " + participantID + " is late and has already taken their " + m1);
                            }
                            else {
                                MainActivity.scantext.setText("Participant " + participantID + " has already taken their " + m1);

                            }
                            MainActivity.upcomingact.setVisibility(View.GONE);
                            MainActivity.currentact.setVisibility(View.GONE);
                            MainActivity.crossImageView.setVisibility(View.VISIBLE);
                            MainActivity.scantext.setVisibility(View.VISIBLE);
                            MainActivity.textClock.setVisibility(View.GONE);
                            MainActivity.tickImageView.setVisibility(View.GONE);
                            MainActivity.cautionImageView.setVisibility(View.GONE);
                            onBackPressed();
                        }
                        else {
                            // The value is false or null, indicating it hasn't been recorded before or is unset
                            // Proceed to set the value to true
                            dbref.child(participantID).child(m1).setValue(true)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @SuppressLint("SetTextI18n")
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                if (Objects.equals(m1, "Meal")) {
                                                    MainActivity.scantext.setText("Participant " + participantID + " is late for their " + m1);
                                                    dbref.child(participantID).child(m1).setValue(true);
                                                    MainActivity.cautionImageView.setVisibility(View.VISIBLE);
                                                    MainActivity.tickImageView.setVisibility(View.GONE);
                                                }
                                                else {
                                                    MainActivity.scantext.setText("Participant " + participantID + ", enjoy your " + m1);
                                                    MainActivity.tickImageView.setVisibility(View.VISIBLE);
                                                    MainActivity.cautionImageView.setVisibility(View.GONE);

                                                }
                                                MainActivity.upcomingact.setVisibility(View.GONE);
                                                MainActivity.currentact.setVisibility(View.GONE);
                                                MainActivity.crossImageView.setVisibility(View.GONE);
                                                MainActivity.scantext.setVisibility(View.VISIBLE);
                                                MainActivity.textClock.setVisibility(View.GONE);
                                                onBackPressed();
                                            }
                                            else {
                                                MainActivity.scantext.setText("Failed to record meal. Please try again.");
                                                MainActivity.scantext.setVisibility(View.VISIBLE);
                                            }
                                            onBackPressed(); // Going back after scanning
                                        }
                                    });
                        }
                    }
                    else {
                        // Handle the case where the value doesn't exist or is null
                        // Proceed to set the value to true as it doesn't exist or is null
                        dbref.child(participantID).child(m1).setValue(true)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            if (Objects.equals(m1, "Meal")) {
                                                dbref.child(participantID).child(m1).setValue(true);
                                                MainActivity.scantext.setText("Participant " + participantID + " is late for their " + m1);
                                                MainActivity.cautionImageView.setVisibility(View.VISIBLE);
                                                MainActivity.tickImageView.setVisibility(View.GONE);

                                            }
                                            else {
                                                MainActivity.scantext.setText("Participant " + participantID + ", enjoy your " + m1);
                                                MainActivity.tickImageView.setVisibility(View.VISIBLE);
                                                MainActivity.cautionImageView.setVisibility(View.GONE);

                                            }
                                            MainActivity.upcomingact.setVisibility(View.GONE);
                                            MainActivity.currentact.setVisibility(View.GONE);
                                            MainActivity.crossImageView.setVisibility(View.GONE);
                                            MainActivity.scantext.setVisibility(View.VISIBLE);
                                            MainActivity.textClock.setVisibility(View.GONE);
                                            onBackPressed();
                                        }
                                        else {
                                            MainActivity.scantext.setText("Failed to record meal. Please try again.");
                                            MainActivity.scantext.setVisibility(View.VISIBLE);
                                        }

                                        onBackPressed(); // Going back after scanning
                                    }
                                });
                    }
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle potential errors here
                    MainActivity.scantext.setText("Error reading value from Firebase Database: " + databaseError.getMessage());
                }
            });
        }
        else if (test==false){
            assert encodedUrl != null;
            dbref1.child(encodedUrl).setValue(participantID);
            MainActivity.scantext.setText("This is an invalid QR");
            MainActivity.upcomingact.setVisibility(View.GONE);
            MainActivity.currentact.setVisibility(View.GONE);
            MainActivity.crossImageView.setVisibility(View.VISIBLE);
            MainActivity.scantext.setVisibility(View.VISIBLE);
            MainActivity.textClock.setVisibility(View.GONE);
            MainActivity.tickImageView.setVisibility(View.GONE);
            MainActivity.cautionImageView.setVisibility(View.GONE);
            onBackPressed();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera(); // Stopping camera when activity is paused
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this); // Setting result handler for ZXingScannerView
        scannerView.startCamera(); // Starting camera when activity is resumed
    }

}
