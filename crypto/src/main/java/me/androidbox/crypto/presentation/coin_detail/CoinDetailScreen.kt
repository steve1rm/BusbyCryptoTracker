@file:OptIn(ExperimentalLayoutApi::class)

package me.androidbox.crypto.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.core.presentation.ui.theme.BusbyCryptoTrackerTheme
import me.androidbox.core.presentation.ui.theme.greenBackground
import me.androidbox.core.presentation.util.getDrawableIdForCoin
import me.androidbox.crypto.R
import me.androidbox.crypto.presentation.CoinListState
import me.androidbox.crypto.presentation.coin_detail.components.CoinInfoCard
import me.androidbox.crypto.presentation.coin_list.components.previewCoin
import me.androidbox.crypto.presentation.models.toCoinUi
import me.androidbox.crypto.presentation.models.toDisplayable

@Composable
fun CoinDetailScreen(
    coinListState: CoinListState,
    modifier: Modifier = Modifier
) {
    val contentColor = if(isSystemInDarkTheme()) { Color.White } else { Color.Black }

    if(coinListState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    else if(coinListState.selectedCoin != null) {
        val coin = coinListState.selectedCoin

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = ImageVector.vectorResource(getDrawableIdForCoin(coinListState.selectedCoin.symbol)),
                contentDescription = coin.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(100.dp)
            )

            Text(
                text = coin.name,
                color = contentColor,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black,
            )

            Text(
                text = coinListState.selectedCoin.symbol,
                textAlign = TextAlign.Center,
                color = contentColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CoinInfoCard(
                    title = stringResource(R.string.market_cap),
                    formattedText = coin.marketCapUsd.formatted,
                    icon = ImageVector.vectorResource(me.androidbox.core.R.drawable.stock),
                    contentColor = contentColor,
                )

                CoinInfoCard(
                    title = stringResource(R.string.price),
                    formattedText = coin.priceUsd.formatted,
                    icon = ImageVector.vectorResource(me.androidbox.core.R.drawable.dollar),
                    contentColor = contentColor,
                )

                val absoluteChangeFormatted =
                    ((coin.priceUsd.value * coin.changePercent24Hrs.value) / 100).toDisplayable()

                val isPositive = coin.changePercent24Hrs.value > 0.0
                val percentageColor = if(isPositive) {
                    if(isSystemInDarkTheme()) {
                        Color.Green
                    }
                    else {
                        greenBackground
                    }
                } else {
                    MaterialTheme.colorScheme.error
                }

                CoinInfoCard(
                    title = stringResource(R.string.change_last_24hrs),
                    formattedText = absoluteChangeFormatted.formatted,
                    icon = if(isPositive) ImageVector.vectorResource(me.androidbox.core.R.drawable.trending) else ImageVector.vectorResource(
                        me.androidbox.core.R.drawable.trending_down),
                    contentColor = percentageColor
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewCoinListScreen() {
    BusbyCryptoTrackerTheme {
        CoinDetailScreen(
            coinListState = CoinListState(
                selectedCoin = previewCoin.toCoinUi()
            ),
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        )
    }
}