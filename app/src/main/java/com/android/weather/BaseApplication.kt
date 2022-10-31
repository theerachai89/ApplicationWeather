package com.android.weather

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.android.weather.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(networkingModule, repositoryModule, useCaseModule, viewModelModule))

            androidLogger()
        }
    }
}