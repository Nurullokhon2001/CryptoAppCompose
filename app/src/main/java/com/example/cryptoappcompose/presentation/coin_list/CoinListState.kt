package com.example.cryptoappcompose.presentation.coin_list

import com.example.cryptoappcompose.domain.model.CoinModel

data class CoinListState(
    val isLoading: Boolean = false,
    val data: List<CoinModel> = emptyList(),
    val error: String = "",
)
