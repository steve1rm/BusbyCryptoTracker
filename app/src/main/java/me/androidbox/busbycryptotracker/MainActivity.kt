package me.androidbox.busbycryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.androidbox.core.presentation.ui.theme.BusbyCryptoTrackerTheme
import me.androidbox.crypto.presentation.coin_list.CoinListScreen
import me.androidbox.crypto.presentation.coin_list.CoinListViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusbyCryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val coinListViewModel = koinViewModel<CoinListViewModel>()
                    val coinListState by coinListViewModel.coinListStateFlow.collectAsStateWithLifecycle()

                    CoinListScreen(
                        coinListState = coinListState
                    )
                }
            }
        }
    }
}
