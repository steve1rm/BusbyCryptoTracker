package me.androidbox.crypto.presentation.coin_list

import me.androidbox.crypto.presentation.models.CoinUi

/** MVI (Intent) which is the actions the user can perform on the screen
 *  Actions (Events) that are send from the UI to the ViewModel */
sealed interface CoinListAction {
    data class OnClickedCoin(val coinUi: CoinUi) : CoinListAction
}