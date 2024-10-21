package me.androidbox.busbycryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.androidbox.core.presentation.ui.theme.BusbyCryptoTrackerTheme
import me.androidbox.core.presentation.util.ObserveAsEvents
import me.androidbox.core.presentation.util.toString
import me.androidbox.crypto.presentation.coin_detail.CoinDetailScreen
import me.androidbox.crypto.presentation.coin_list.CoinListAction
import me.androidbox.crypto.presentation.coin_list.CoinListEvent
import me.androidbox.crypto.presentation.coin_list.CoinListScreen
import me.androidbox.crypto.presentation.coin_list.CoinListViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   enableEdgeToEdge()
        setContent {
            BusbyCryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val coinListViewModel = koinViewModel<CoinListViewModel>()
                    val coinListState by coinListViewModel.coinListStateFlow.collectAsStateWithLifecycle()
                    val coinListEvent = coinListViewModel.eventChannel

                    ObserveAsEvents(coinListEvent) { coinEvent ->
                        when(coinEvent) {
                            is CoinListEvent.OnErrorEvent -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    coinEvent.errorMessage.toString(this@MainActivity),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    if(coinListState.selectedCoin != null) {
                        CoinDetailScreen(
                            coinListState = coinListState,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                    else {
                        CoinListScreen(
                            coinListState = coinListState,
                            onCoinClicked = { coinUi ->
                                coinListViewModel.onAction(CoinListAction.OnClickedCoin(coinUi))
                            }
                        )
                    }
                }
            }
        }
    }
}
