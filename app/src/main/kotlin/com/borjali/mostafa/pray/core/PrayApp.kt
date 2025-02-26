package com.borjali.mostafa.pray.core

import android.app.Application
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
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
import java.io.File


class PrayApp : Application() {

    companion object {
        lateinit var instance: PrayApp

        @get:UnstableApi
        val simpleCache by lazy {
            initCache()
        }

        @androidx.annotation.OptIn(UnstableApi::class)
        private fun initCache(): SimpleCache {
            val cacheSize = 1024 * 1024 * 1024 // 1GB cache
            val cacheEvictor = LeastRecentlyUsedCacheEvictor(cacheSize.toLong())
            val databaseProvider = StandaloneDatabaseProvider(instance)
            return SimpleCache(File(instance.cacheDir, "media"), cacheEvictor, databaseProvider)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
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