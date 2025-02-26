package com.borjali.mostafa.pray.domain.repository

import com.borjali.mostafa.pray.domain.model.AppResult
import com.borjali.mostafa.pray.domain.model.Menu

interface MenuRepository {
    suspend fun getListOfMenu(type: Any?): AppResult<List<Menu>>
}