package com.example.intelliqr;

import java.time.LocalTime; // import the LocalTime class
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.Manifest;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    String m1 = "Meal 1";
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
            LocalTime currentTime = LocalTime.now();
            LocalTime Morning = LocalTime.of(8, 0);
            LocalTime morning = LocalTime.of(11, 0);

            LocalTime Afternoon = LocalTime.of(14, 0);
            LocalTime afternoon = LocalTime.of(15, 0);

            LocalTime Night = LocalTime.of(21, 0);
            LocalTime night = LocalTime.of(22, 0);

            LocalTime gulu = LocalTime.of(21, 0);
            LocalTime GULU = LocalTime.of(23, 59);

            if (currentTime.isAfter(Morning) & (currentTime.isBefore(morning))) {
                m1 = "Meal1";
            }
            else if (currentTime.isAfter(Afternoon)& (currentTime.isBefore(afternoon))) {
                m1 = "Meal2";
            }
            else if (currentTime.isAfter(Night)& (currentTime.isBefore(night))){
                m1 = "Meal3";
            }
            else if(currentTime.isAfter(gulu)& (currentTime.isBefore(GULU))){
                m1="ishank bhaiya";
            }
        }

        // Assuming participantID is the unique identifier for each participant
        dbref.child(participantID).child(m1).setValue(true)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            MainActivity.scantext.setText(m1 +" for Participant " + participantID + " recorded successfully");
                        } else {
                            MainActivity.scantext.setText("Failed to record meal. Please try again.");
                        }
                        onBackPressed(); // Going back after scanning
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
