package com.borjali.mostafa.pray.core

import android.app.Application
import com.borjali.mostafa.pray.BuildConfig
import com.borjali.mostafa.pray.di.databaseModule
import com.borjali.mostafa.pray.di.repositoryModule
import com.borjali.mostafa.pray.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree


open class PrayApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.NONE)
            androidContext(this@PrayApp)
            modules(listOf(viewModelModule, repositoryModule, databaseModule))
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}