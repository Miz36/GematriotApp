package com.example.tanach

import android.app.Application
import com.example.tanach.network.SupabaseClient

class TanachApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SupabaseClient.init(this)
    }
}
