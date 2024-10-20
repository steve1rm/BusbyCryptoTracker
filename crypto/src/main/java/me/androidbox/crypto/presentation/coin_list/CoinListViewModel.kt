package me.androidbox.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.core.domain.util.onError
import me.androidbox.core.domain.util.onSuccess
import me.androidbox.crypto.presentation.CoinListState
import me.androidbox.crypto.presentation.datasource.CoinRemoteDataSource
import me.androidbox.crypto.presentation.models.toCoinUi

class CoinListViewModel(
    private val coinRemoteDataSource: CoinRemoteDataSource
) : ViewModel() {

    private val _eventChannel = Channel<CoinListEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    private val _coinlistStateFlow = MutableStateFlow(CoinListState())
    val coinListStateFlow = _coinlistStateFlow.asStateFlow()
        .onStart {
            loadCoins()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CoinListState()
        )

    fun onAction(coinListAction: CoinListAction) {
        when(coinListAction) {
            is CoinListAction.OnClickedCoin -> {
                /** Navigate to detail screen */
            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _coinlistStateFlow.update { coinListState ->
                coinListState.copy(
                    isLoading = true
                )
            }

            coinRemoteDataSource.fetchCoins()
                .onSuccess { coinList ->
                    _coinlistStateFlow.update { coinListState ->
                        coinListState.copy(
                            coins = coinList.map { coin ->
                                coin.toCoinUi()
                            },
                            isLoading = false
                        )
                    }
                }
                .onError { networkError ->
                    _coinlistStateFlow.update { coinListState ->
                        coinListState.copy(
                            isLoading = false)
                    }
                    _eventChannel.send(CoinListEvent.OnErrorEvent(networkError))
                }
        }
    }
}