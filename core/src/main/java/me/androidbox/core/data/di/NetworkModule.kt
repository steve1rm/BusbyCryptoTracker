package me.androidbox.core.data.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import me.androidbox.core.data.networking.HttpClientFactory
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClientFactory.create(CIO.create())
    }
}