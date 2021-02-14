package com.borjali.mostafa.pray.di

import com.borjali.mostafa.pray.data.db.MenuDao
import com.borjali.mostafa.pray.data.db.PrayDao
import com.borjali.mostafa.pray.data.repository.MenuRepositoryImpl
import com.borjali.mostafa.pray.data.repository.NamazRepositoryImpl
import com.borjali.mostafa.pray.domain.repository.MenuRepository
import com.borjali.mostafa.pray.domain.repository.NamazRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideNamazRepository(namazDao: PrayDao,menuDao: MenuDao): NamazRepository {
        return NamazRepositoryImpl(namazDao,menuDao)
    }

    single { provideNamazRepository(namazDao = get(),menuDao = get()) }

    /*    fun provideMenuRepository(dao: MenuDao): MenuRepository {
        return MenuRepositoryImpl(dao)
    }

    single { provideMenuRepository(get()) }*/

}