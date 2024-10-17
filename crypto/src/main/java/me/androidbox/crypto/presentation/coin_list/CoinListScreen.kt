package me.androidbox.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import me.androidbox.core.presentation.ui.theme.BusbyCryptoTrackerTheme
import me.androidbox.crypto.presentation.CoinListState
import me.androidbox.crypto.presentation.coin_list.components.CoinListItem
import me.androidbox.crypto.presentation.coin_list.components.previewCoin
import me.androidbox.crypto.presentation.models.toCoinUi

@Composable
fun CoinListScreen(
    coinListState: CoinListState,
    modifier: Modifier = Modifier
) {
    if(coinListState.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
    else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = coinListState.coins,
                key = { coinUi ->
                    coinUi.id
                }
            ) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onCoinClicked = { coinId ->
                        println(coinId)
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun PreviewCoinListScreen() {
    BusbyCryptoTrackerTheme {
        CoinListScreen(
            coinListState = CoinListState(
                coins = (1..100).map {
                    previewCoin.copy(id = it.toString()).toCoinUi()
                }
            ),
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
        )
    }
}