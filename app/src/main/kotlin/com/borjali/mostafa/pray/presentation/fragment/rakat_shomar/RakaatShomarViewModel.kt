package com.borjali.mostafa.pray.presentation.fragment.rakat_shomar


import android.annotation.SuppressLint
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjali.mostafa.pray.domain.model.AppResult
import com.borjali.mostafa.pray.domain.model.Pray
import com.borjali.mostafa.pray.domain.repository.NamazRepository
import com.borjali.mostafa.pray.utils.SingleLiveEvent
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

class RakaatShomarViewModel(private val prayRepository: NamazRepository) : ViewModel() {
    val listOfTaaghibat = MutableLiveData<List<Pray>?>()
    private val showError = SingleLiveEvent<String>()
    private val showLoading = ObservableBoolean()

    @SuppressLint("TimberArgCount")
    fun getListOfNamaz(groupId: Int) {
        viewModelScope.launch {
            Timber.e("ERROR IS  : ")
            when (val result = prayRepository.getListOfNamaz(groupId)) {
                is AppResult.Success -> {
                    listOfTaaghibat.value = result.successData
                    Timber.e(result.successData.toString())

                }

                is AppResult.Error -> {
                    showError.value = result.error.localizedMessage
                    Timber.e("ERROR IS  : ", result.error.localizedMessage)
                }
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}