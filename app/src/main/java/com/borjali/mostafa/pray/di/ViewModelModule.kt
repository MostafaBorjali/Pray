package com.borjali.mostafa.pray.di

import com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.NamazMostahabiViewModel
import com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb.NamazVajebViewModel
import com.borjali.mostafa.pray.presentation.fragment.rakat_shomar.RakaatShomarViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { NamazMostahabiViewModel( get())}
    viewModel { NamazVajebViewModel(get()) }
    viewModel { RakaatShomarViewModel(get()) }
}