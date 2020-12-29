package com.borjali.mostafa.pray.di

import android.app.Application
import androidx.room.Room
import com.borjali.mostafa.pray.data.db.MenuDao
import com.borjali.mostafa.pray.data.db.PrayDao
import com.borjali.mostafa.pray.data.db.PrayDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): PrayDataBase {
        return Room.databaseBuilder(application, PrayDataBase::class.java, "pray.db")
            .createFromAsset("databases/pray.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePrayDao(database: PrayDataBase): PrayDao {
        return  database.prayDao()
    }

    fun provideMenuDao(database: PrayDataBase): MenuDao {
        return  database.menuDao()
    }


    single { provideDatabase(androidApplication()) }
    single { providePrayDao(get()) }
    single { provideMenuDao(get()) }


}