package org.d3if4056.homestaypet

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class modeNight: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_YES )
    }
}