package com.example.lifecyclesandbox;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    private static final String TAG = "Activity_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Log.d(TAG, "onCreate(): " + Activity2.class.getCanonicalName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart(): " + Activity2.class.getCanonicalName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume(): " + Activity2.class.getCanonicalName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart(): " + Activity2.class.getCanonicalName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause(): " + Activity2.class.getCanonicalName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop(): " + Activity2.class.getCanonicalName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy(): " + Activity2.class.getCanonicalName());
    }
}
