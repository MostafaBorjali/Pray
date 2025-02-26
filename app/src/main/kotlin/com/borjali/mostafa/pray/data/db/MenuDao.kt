package com.borjali.mostafa.pray.data.db

import androidx.room.Dao
import androidx.room.Query
import com.borjali.mostafa.pray.domain.model.Menu

@Dao
interface MenuDao {

    @Query("SELECT * FROM menu WHERE type LIKE :type")
    fun getListOfMenu(type: Int): List<Menu>
}