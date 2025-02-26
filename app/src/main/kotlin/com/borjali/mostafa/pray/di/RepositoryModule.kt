package com.borjali.mostafa.pray.di

import com.borjali.mostafa.pray.data.db.MenuDao
import com.borjali.mostafa.pray.data.db.PrayDao
import com.borjali.mostafa.pray.data.db.TutorialDao
import com.borjali.mostafa.pray.data.repository.NamazRepositoryImpl
import com.borjali.mostafa.pray.domain.repository.NamazRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideNamazRepository(
        namazDao: PrayDao,
        menuDao: MenuDao,
        tutorialDao: TutorialDao
    ): NamazRepository {
        return NamazRepositoryImpl(namazDao, menuDao, tutorialDao)
    }

    single { provideNamazRepository(namazDao = get(), menuDao = get(), tutorialDao = get()) }

}