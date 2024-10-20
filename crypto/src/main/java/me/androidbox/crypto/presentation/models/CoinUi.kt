package me.androidbox.crypto.presentation.models

import androidx.annotation.DrawableRes
import me.androidbox.core.presentation.util.getDrawableIdForCoin
import me.androidbox.crypto.domain.Coin
import android.icu.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hrs: DisplayableNumber,
    @DrawableRes val iconRes: Int
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = this.id,
        rank = this.rank,
        name = this.name,
        symbol = this.symbol,
        marketCapUsd = this.marketCapUsd.toDisplayable(),
        priceUsd = this.priceUsd.toDisplayable(),
        changePercent24Hrs = this.changePercent24Hrs.toDisplayable(),
        iconRes = getDrawableIdForCoin(this.symbol)
    )
}

fun Double.toDisplayable(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        this.minimumFractionDigits = 2
        this.maximumFractionDigits = 2
    }

    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}