package com.borjali.mostafa.pray.data.repository

import com.borjali.mostafa.pray.data.db.PrayDao
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.domain.repository.NamazRepository
import com.borjali.mostafa.pray.domain.model.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NamazRepositoryImpl(private val dao: PrayDao) : NamazRepository {

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

    private suspend fun getListOfNamaz(type: Int): List<Pray> {
        return withContext(Dispatchers.IO) {
            dao.getlistOfNamazVajeb(type)
        }
    }


     //This is another way of implementing
  /*  override suspend fun getListOfNamazMostahabi(type: Any?): List<Pray> {
        return dao.getlistOfNamazVajeb(type = type as Int)
    }*/
}