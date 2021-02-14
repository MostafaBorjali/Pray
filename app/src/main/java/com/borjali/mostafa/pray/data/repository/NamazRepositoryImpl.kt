package com.borjali.mostafa.pray.data.repository

import com.borjali.mostafa.pray.data.db.MenuDao
import com.borjali.mostafa.pray.data.db.PrayDao
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.domain.repository.NamazRepository
import com.borjali.mostafa.pray.domain.model.AppResult
import com.borjali.mostafa.pray.domain.model.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NamazRepositoryImpl(private val namazDao: PrayDao,private val menuDao: MenuDao) : NamazRepository {

    override suspend fun getListOfNamaz(type: Any?): AppResult<List<Pray>> {
        val data = getListOfNamaz(type as Int)
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
        }    }
    private suspend fun getListOfNamaz(type: Int): List<Pray> {
        return withContext(Dispatchers.IO) {
            namazDao.getlistOfNamazVajeb(type)
        }
    }
    private suspend fun getListOfMenu(type: Int): List<Menu> {
        return withContext(Dispatchers.IO) {
            menuDao.getlistOfMenu(type)
        }
    }


     //This is another way of implementing
  /*  override suspend fun getListOfNamazMostahabi(type: Any?): List<Pray> {
        return dao.getlistOfNamazVajeb(type = type as Int)
    }*/
}