package com.borjali.mostafa.pray.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray

@Database(entities = [Pray::class,Menu::class], version = 1,exportSchema = false)
abstract class PrayDataBase : RoomDatabase() {
    abstract fun prayDao(): PrayDao
    abstract fun menuDao(): MenuDao
}
