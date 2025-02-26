package com.borjali.mostafa.pray.domain.model

sealed class NetworkStatus {
    data object Available : NetworkStatus()
    data object Unavailable : NetworkStatus()
}
