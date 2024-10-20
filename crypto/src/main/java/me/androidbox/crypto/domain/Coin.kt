package me.androidbox.crypto.domain

data class Coin(
    val id: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val priceUsd: Double,
    val marketCapUsd: Double,
    val changePercent24Hrs: Double
)
