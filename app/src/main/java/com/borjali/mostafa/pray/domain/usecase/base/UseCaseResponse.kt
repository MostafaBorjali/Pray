package com.borjali.mostafa.pray.domain.usecase.base


interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(error: Throwable)
}

