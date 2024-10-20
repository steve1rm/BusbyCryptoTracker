package me.androidbox.crypto.presentation

import androidx.compose.runtime.Immutable
import me.androidbox.crypto.presentation.models.CoinUi

@Immutable
data class CoinListState(
    val coins: List<CoinUi> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCoin: CoinUi? = null
)
