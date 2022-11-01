package com.example.cryptoappcompose.presentation

sealed class Screen(val route: String) {
    object CoinListScreen : Screen("coin_list_screen")
    object CoinDetailsListScreen : Screen("coin_detail_screen")
}
