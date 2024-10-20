package me.androidbox.crypto.data.mappers

import me.androidbox.crypto.data.dto.CoinDto
import me.androidbox.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = this.id,
        name = this.name,
        rank = this.rank,
        symbol = this.symbol,
        priceUsd = this.priceUsd,
        marketCapUsd = this.marketCapUsd,
        changePercent24Hrs = this.changePercent24Hr
    )
}