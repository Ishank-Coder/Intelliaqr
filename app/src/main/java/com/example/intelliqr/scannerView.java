package com.example.intelliqr;

import java.time.LocalDate;
import java.time.LocalTime; // import the LocalTime class
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.Manifest;

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
    DatabaseReference dbref; // Firebase database reference object
    String m1 = "Meal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this); // Initializing ZXingScannerView
        setContentView(scannerView); // Setting the content view to ZXingScannerView
        dbref = FirebaseDatabase.getInstance().getReference().child("hackathon").child("participants"); // Getting reference to Firebase database

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
    @Override
    public void handleResult(Result rawResult) {
        final String participantID = rawResult.getText();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();
            LocalDate date1 = LocalDate.parse("2024-04-06");  // Parse from a String
            LocalDate date2 = LocalDate.parse("2024-04-07");    // parse from a string

            if (today.equals(date1)) {
                LocalTime currentTime = LocalTime.now();
                LocalTime Morning = LocalTime.of(8, 0);
                LocalTime morning = LocalTime.of(9, 0);

                LocalTime Afternoon = LocalTime.of(13, 0);
                LocalTime afternoon = LocalTime.of(20, 0);

                LocalTime Night = LocalTime.of(21, 0);
                LocalTime night = LocalTime.of(22, 0);

                if (currentTime.isAfter(Morning) & (currentTime.isBefore(morning))) {
                    m1 = "Breakfast";
                } else if (currentTime.isAfter(Afternoon) & (currentTime.isBefore(afternoon))) {
                    m1 = "Lunch";
                } else if (currentTime.isAfter(Night) & (currentTime.isBefore(night))) {
                    m1 = "Dinner";
                }
            } else if (today.equals(date2)) {
                LocalTime currentTime = LocalTime.now();
                LocalTime Morning = LocalTime.of(8, 0);
                LocalTime morning = LocalTime.of(9, 0);

                LocalTime Afternoon = LocalTime.of(14, 0);
                LocalTime afternoon = LocalTime.of(15, 0);

                if (currentTime.isAfter(Morning) & (currentTime.isBefore(morning))) {
                    m1 = "Breakfast2";
                } else if (currentTime.isAfter(Afternoon) & (currentTime.isBefore(afternoon))) {
                    m1 = "Lunch2";
                }
            }
        }

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
                        if (Objects.equals(m1, "Meal")){
                            MainActivity.scantext.setText("Participant "+ participantID+" is late and has already taken their " + m1);
                        }
                        else{
                            MainActivity.scantext.setText("Participant " + participantID + " has already taken their " + m1);
                        }
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
                                            }
                                            else{
                                                MainActivity.scantext.setText("Participant " + participantID + ", enjoy your " + m1);
                                            }
                                            onBackPressed();
                                        }
                                        else {
                                            MainActivity.scantext.setText("Failed to record meal. Please try again.");
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
                                            MainActivity.scantext.setText("Participant " + participantID + " is late for their " + m1);
                                        }
                                        else{
                                            MainActivity.scantext.setText("Participant " + participantID + ", enjoy your " + m1);
                                        }
                                        onBackPressed();
                                    }
                                    else {
                                        MainActivity.scantext.setText("Failed to record meal. Please try again.");
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
