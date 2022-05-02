package com.example.lifecyclesandbox.login

import com.example.lifecyclesandbox.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity(R.layout.login_activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.activity_1_fragment_container_view, LoginFragment::class.java, null)
                    .commit()
        }
        setContentView(R.layout.login_activity)
        Log.d(TAG, "onCreate(): " + LoginActivity::class.java.canonicalName)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart(): " + LoginActivity::class.java.canonicalName)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume(): " + LoginActivity::class.java.canonicalName)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart(): " + LoginActivity::class.java.canonicalName)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause(): " + LoginActivity::class.java.canonicalName)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop(): " + LoginActivity::class.java.canonicalName)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy(): " + LoginActivity::class.java.canonicalName)
    }

    companion object {
        private const val TAG = "Activity_1"
    }
}