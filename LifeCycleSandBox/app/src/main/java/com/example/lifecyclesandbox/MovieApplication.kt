package com.example.lifecyclesandbox

import android.app.Application
import com.example.lifecyclesandbox.db.LocalDataSource

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalDataSource.applicationContext = this
    }
}