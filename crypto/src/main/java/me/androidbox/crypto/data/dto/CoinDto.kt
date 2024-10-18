package me.androidbox.crypto.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double,
)
