package com.borjali.mostafa.pray.di

import com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.NamazMostahabiViewModel
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { NamazMostahabiViewModel(get())}
    viewModel { NamazVajebViewModel(repository = get(),prayRepository = get()) }
}