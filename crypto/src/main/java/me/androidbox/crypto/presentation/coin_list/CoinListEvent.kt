package me.androidbox.crypto.presentation.coin_list

import me.androidbox.core.domain.util.NetworkError

/** Events that from the ViewModel to the UI */
sealed interface CoinListEvent {
    data class OnErrorEvent(val errorMessage: NetworkError) : CoinListEvent
}