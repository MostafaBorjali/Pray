package com.borjali.mostafa.pray.presentation.fragment.amozesh

import android.annotation.SuppressLint
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjali.mostafa.pray.domain.model.AppResult
import com.borjali.mostafa.pray.domain.model.Menu
import com.borjali.mostafa.pray.domain.model.Tutorial
import com.borjali.mostafa.pray.domain.repository.NamazRepository
import com.borjali.mostafa.pray.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class AmozeshViewModel(private val prayRepository: NamazRepository) : ViewModel() {
    val listOfMenu = MutableLiveData<List<Menu>?>()
    val listOfTutorialVideos = MutableLiveData<List<Tutorial>>()
    private val showError = SingleLiveEvent<String>()
    private val showLoading = ObservableBoolean()

    @SuppressLint("NullSafeMutableLiveData")
    fun getListOfMenu(type: Int) {
        showLoading.set(true)
        viewModelScope.launch {
            val result = prayRepository.getListOfMenu(type)
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    listOfMenu.value = result.successData
                    showError.value = null
                }

                is AppResult.Error -> showError.value = result.error.localizedMessage
            }
        }
    }

    fun getListOfTutorials(groupId: Int) {
        viewModelScope.launch {
            when (val result = prayRepository.getListOfTutorialVideos(groupId)) {
                is AppResult.Success -> {
                    listOfTutorialVideos.value = result.successData
                }

                is AppResult.Error -> {
                    showError.value = result.error.localizedMessage
                }
            }
        }
    }

}
