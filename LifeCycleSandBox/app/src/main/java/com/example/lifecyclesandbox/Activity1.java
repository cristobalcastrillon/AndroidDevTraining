package com.example.lifecyclesandbox;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Activity1 extends AppCompatActivity {

    private static final String TAG = "Activity_1";

    // Default constructor
    public Activity1() {
        super(R.layout.activity_1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.activity_1_fragment_container_view, Fragment1.class, null)
                    .commit();
        }
        setContentView(R.layout.activity_1);
        Log.d(TAG, "onCreate(): " + Activity1.class.getCanonicalName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart(): " + Activity1.class.getCanonicalName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume(): " + Activity1.class.getCanonicalName());
    }

    @Override
    protected void onRestart() {
        super.onResume();
        Log.d(TAG, "onRestart(): " + Activity1.class.getCanonicalName());
    }

    @Override
    protected void onPause() {
        super.onResume();
        Log.d(TAG, "onPause(): " + Activity1.class.getCanonicalName());
    }

    @Override
    protected void onStop() {
        super.onResume();
        Log.d(TAG, "onStop(): " + Activity1.class.getCanonicalName());
    }

    @Override
    protected void onDestroy() {
        super.onResume();
        Log.d(TAG, "onDestroy(): " + Activity1.class.getCanonicalName());
    }

}