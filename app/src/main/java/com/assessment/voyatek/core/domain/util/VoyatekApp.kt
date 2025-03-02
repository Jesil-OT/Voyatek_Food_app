package com.assessment.voyatek.core.domain.util

import android.app.Application
import com.assessment.voyatek.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VoyatekApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VoyatekApp)
            androidLogger()
            modules(appModule)
        }
    }
}