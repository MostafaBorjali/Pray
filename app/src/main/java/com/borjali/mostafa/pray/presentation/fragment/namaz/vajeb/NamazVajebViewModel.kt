package com.borjali.mostafa.pray.presentation.fragment.namaz.vajeb

import android.annotation.SuppressLint
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjali.mostafa.pray.domain.model.AppResult
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.domain.repository.NamazRepository
import com.borjali.mostafa.pray.presentation.fragment.namaz.mostahabi.NamazMostahabiViewModel
import com.borjali.mostafa.pray.utils.SingleLiveEvent
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NamazVajebViewModel(private val prayRepository: NamazRepository) : ViewModel() {
    private val TAG = NamazMostahabiViewModel::class.java.name
    val listOfNamazMenu = MutableLiveData<List<Menu>?>()
    val listOfNamazVajeb = MutableLiveData<List<Pray>>()
    private val showError = SingleLiveEvent<String>()
    private val showLoading = ObservableBoolean()

    @SuppressLint("NullSafeMutableLiveData")
    fun getListOfMenuNamazVajeb(type: Int) {
        showLoading.set(true)
        viewModelScope.launch {
            val result = prayRepository.getListOfMenu(type)
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    listOfNamazMenu.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.error.localizedMessage
            }
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun getListOfNamaz(groupId: Int) {
        viewModelScope.launch {
            when (val result = prayRepository.getListOfNamaz(groupId)) {
                is AppResult.Success -> {
                    listOfNamazVajeb.value = result.successData
                }
                is AppResult.Error -> {
                    showError.value = result.error.localizedMessage
                }
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}