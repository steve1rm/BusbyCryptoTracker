package me.androidbox.crypto.presentation.di

import me.androidbox.crypto.presentation.coin_list.CoinListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val coinListModule = module {
    viewModelOf(::CoinListViewModel)
}