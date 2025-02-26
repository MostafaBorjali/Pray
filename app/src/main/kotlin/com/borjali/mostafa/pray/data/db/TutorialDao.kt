package com.borjali.mostafa.pray.data.db

import androidx.room.Dao
import androidx.room.Query
import com.borjali.mostafa.pray.domain.model.Tutorial

@Dao
interface TutorialDao {

    @Query("SELECT * FROM amoozesh WHERE groupId LIKE :groupId")
    fun getListOfTutorial(groupId: Int): List<Tutorial>
}