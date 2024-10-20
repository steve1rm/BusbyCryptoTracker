@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.core.presentation.ui.theme.BusbyCryptoTrackerTheme
import me.androidbox.core.presentation.util.getDrawableIdForCoin
import me.androidbox.crypto.domain.Coin
import me.androidbox.crypto.presentation.models.CoinUi
import me.androidbox.crypto.presentation.models.toCoinUi


@Composable
fun CoinListItem(
    coinUi: CoinUi,
    onCoinClicked: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if(isSystemInDarkTheme()) Color.White else Color.Black

    Row(
        modifier = modifier
            .clickable {
                onCoinClicked(coinUi.id)
            }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = getDrawableIdForCoin(coinUi.symbol)),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(84.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = coinUi.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                color = contentColor
            )

            Text(
                text = coinUi.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$${coinUi.priceUsd.formatted}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor)

            Spacer(modifier = Modifier.height(8.dp))

            PriceChange(
                percentChange = coinUi.changePercent24Hrs
            )
        }
    }
}

@Preview(showBackground = true)
@PreviewLightDark
// @PreviewDynamicColors
@Composable
fun PreviewCoinListItem() {
    BusbyCryptoTrackerTheme {
        CoinListItem(coinUi = previewCoin.toCoinUi(),
            onCoinClicked = {},
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background))
    }
}

internal val previewCoin = Coin(
    id = "bitcoin",
    rank = 1,
    name = "BitCoin",
    symbol = "BTC",
    marketCapUsd = 878340265.75,
    priceUsd = 867239.15,
    changePercent24Hrs = -0.6
)