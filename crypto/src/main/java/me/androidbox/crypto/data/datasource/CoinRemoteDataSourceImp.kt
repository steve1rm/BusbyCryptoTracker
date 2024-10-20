package me.androidbox.crypto.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import me.androidbox.core.data.networking.constructUrl
import me.androidbox.core.data.networking.safeCall
import me.androidbox.core.domain.util.NetworkError
import me.androidbox.core.domain.util.Result
import me.androidbox.core.domain.util.map
import me.androidbox.crypto.data.dto.CoinListDto
import me.androidbox.crypto.data.mappers.toCoin
import me.androidbox.crypto.domain.Coin
import me.androidbox.crypto.presentation.datasource.CoinRemoteDataSource

class CoinRemoteDataSourceImp(private val httpClient: HttpClient) : CoinRemoteDataSource {
    override suspend fun fetchCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinListDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { coinListDto ->
            coinListDto.coinDto.map { coinDto ->
                coinDto.toCoin()
            }
        }

    }
}
