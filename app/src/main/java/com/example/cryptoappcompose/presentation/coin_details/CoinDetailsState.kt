package com.example.cryptoappcompose.presentation.coin_details

import com.example.cryptoappcompose.domain.model.CoinDetails

data class CoinDetailsState(
    val isLoading: Boolean = false,
    val data: CoinDetails? = null,
    val error: String = "",
)
