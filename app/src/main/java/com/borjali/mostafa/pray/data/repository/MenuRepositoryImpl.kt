package com.borjali.mostafa.pray.data.repository

import com.borjali.mostafa.pray.data.db.MenuDao
import com.borjali.mostafa.pray.data.db.PrayDao
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.domain.repository.NamazRepository
import com.borjali.mostafa.pray.domain.model.AppResult
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuRepositoryImpl(private val dao: MenuDao) : MenuRepository {

    override suspend fun getListOfMenu(type: Any?): AppResult<List<Menu>> {
        val data = getListOfMenu(type as Int)
        return try {
            if (data.isNotEmpty()) {
                AppResult.Success(data)
            } else {
                AppResult.Success(emptyList())
            }
        } catch (e: Exception){
            return AppResult.Error(e)
        }

    }

    private suspend fun getListOfMenu(type: Int): List<Menu> {
        return withContext(Dispatchers.IO) {
            dao.getlistOfMenu(type)
        }
    }

}