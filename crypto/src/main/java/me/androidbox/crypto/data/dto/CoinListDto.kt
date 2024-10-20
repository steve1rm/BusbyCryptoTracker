package me.androidbox.crypto.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinListDto(
    @SerialName("data")
    val coinDto: List<CoinDto>
)
