package com.thahira.example.officeroomapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OfficeApp: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@OfficeApp)
            modules(listOf(networkModule, appModule, viewModelModule))
        }
    }
}