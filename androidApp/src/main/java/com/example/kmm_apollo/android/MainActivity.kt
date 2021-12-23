package com.example.kmm_apollo.android

import android.app.Application
import com.example.kmm_apollo.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }
}
