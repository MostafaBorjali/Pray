package com.borjali.mostafa.pray.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.domain.model.Tutorial

@Database(entities = [Pray::class, Menu::class, Tutorial::class], version = 2, exportSchema = false)
abstract class PrayDataBase : RoomDatabase() {
    abstract fun prayDao(): PrayDao
    abstract fun menuDao(): MenuDao
    abstract fun tutorialDao(): TutorialDao
}
