package me.androidbox.crypto.data.di

import io.ktor.client.HttpClient
import me.androidbox.crypto.data.datasource.CoinRemoteDataSourceImp
import me.androidbox.crypto.presentation.datasource.CoinRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory<CoinRemoteDataSource> {
        CoinRemoteDataSourceImp(get<HttpClient>())
    }
}