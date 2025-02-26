package com.borjali.mostafa.pray.domain.repository

import com.borjali.mostafa.pray.domain.model.AppResult
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.domain.model.Tutorial

interface NamazRepository {
    suspend fun getListOfNamaz(type: Any?): AppResult<List<Pray>>
    suspend fun getListOfMenu(type: Any?): AppResult<List<Menu>>
    suspend fun getListOfTutorialVideos(type: Any?): AppResult<List<Tutorial>>
}