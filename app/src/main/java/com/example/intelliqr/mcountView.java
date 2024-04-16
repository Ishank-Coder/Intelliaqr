package com.example.intelliqr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mcountView extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static TextView heading;
    @SuppressLint("StaticFieldLeak")
    static TextView mealcountnum;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcountview);
        heading = findViewById(R.id.heading);

        mealcountnum = findViewById(R.id.mealcountnum);



        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("hackathon").child("participants");

        // Fetch total number of entries
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long totalEntries = dataSnapshot.getChildrenCount();
                final String str = Long.toString(totalEntries);
                // Display the total number of entries
                mcountView.mealcountnum.setText(str);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Log.w("Fetch Entries", "Failed to read value.", databaseError.toException());
            }
        });
    }
}
