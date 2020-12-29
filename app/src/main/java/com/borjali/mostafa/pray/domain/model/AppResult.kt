package com.borjali.mostafa.pray.domain.model

/**
 * AppResult class is a wrapper class that helps to handle success and failure scenarios with coroutines
 */
sealed class AppResult<out T> {

    data class Success<out T>(val successData : T) : AppResult<T>()
    class Error(val error: Throwable) : AppResult<Nothing>()

}