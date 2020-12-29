package com.borjali.mostafa.pray.domain.usecase.base

import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

abstract class UseCase<Type, in Params>() where Type : Any {

    abstract suspend fun run(params: Params? = null): Type
    fun invoke(scope: CoroutineScope, params: Params?, onResult: UseCaseResponse<Type>?) {

        scope.launch {
            try {
                val result = run(params)
                onResult?.onSuccess(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult?.onError(e)
            } catch (e: Throwable) {
                e.printStackTrace()
                onResult?.onError(e)
            }
        }
    }

}