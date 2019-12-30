package com.example.quizzit

import android.app.Application
import com.example.quizzit.modules.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(mainModule)
        }
    }
}