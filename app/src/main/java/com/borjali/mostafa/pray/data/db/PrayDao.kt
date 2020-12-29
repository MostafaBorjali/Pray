package com.borjali.mostafa.pray.data.db

import androidx.room.Dao
import androidx.room.Query
import com.borjali.mostafa.pray.domain.model.Pray

@Dao
interface PrayDao {

        @Query("SELECT * FROM mostahabi WHERE groupId LIKE :groupId")
        fun getlistOfNamazVajeb(groupId: Int): List<Pray>
}