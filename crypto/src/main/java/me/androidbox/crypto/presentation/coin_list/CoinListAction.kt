package me.androidbox.crypto.presentation.coin_list

import me.androidbox.crypto.presentation.models.CoinUi

/** MVI (Intent) which is the actions the user can perform on the screen */
sealed interface CoinListAction {
    data class OnClickedCoin(val coinUi: CoinUi) : CoinListAction
}