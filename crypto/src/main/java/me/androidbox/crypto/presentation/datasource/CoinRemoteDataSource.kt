package me.androidbox.crypto.presentation.datasource

import me.androidbox.core.domain.util.NetworkError
import me.androidbox.core.domain.util.Result
import me.androidbox.crypto.domain.Coin

interface CoinRemoteDataSource {
    suspend fun fetchCoins(): Result<List<Coin>, NetworkError>
}