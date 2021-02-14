package com.borjali.mostafa.pray.domain.repository

import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.domain.model.AppResult
import com.borjali.mostafa.pray.domain.model.Menu

interface NamazRepository {
    suspend fun getListOfNamaz(type: Any?) : AppResult<List<Pray>>
    suspend fun getListOfMenu(type: Any?) : AppResult<List<Menu>>
   // suspend fun getListOfNamazMostahabi(type: Any?) : List<Pray>
}