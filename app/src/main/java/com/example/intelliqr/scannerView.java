package com.example.intelliqr;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this); // Initializing ZXingScannerView
        setContentView(scannerView); // Setting the content view to ZXingScannerView
        dbref = FirebaseDatabase.getInstance().getReference("qrdata"); // Getting reference to Firebase database

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
        MainActivity.scantext.setText(rawResult.getText()); // Setting scanned text to MainActivity's TextView
        String data= rawResult.getText();

        dbref.push().setValue(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        MainActivity.scantext.setText("Data inserted Successfully");
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
