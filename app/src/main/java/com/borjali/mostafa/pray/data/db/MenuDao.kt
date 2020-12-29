package com.borjali.mostafa.pray.data.db

import androidx.room.Dao
import androidx.room.Query
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray

@Dao
interface MenuDao {

        @Query("SELECT * FROM menu WHERE type LIKE :type")
        fun getlistOfMenu(type: Int): List<Menu>
}