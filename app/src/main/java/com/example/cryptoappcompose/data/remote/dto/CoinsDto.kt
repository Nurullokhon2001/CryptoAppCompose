package com.example.cryptoappcompose.data.remote.dto

import com.example.cryptoappcompose.domain.model.CoinModel
import com.google.gson.annotations.SerializedName

data class CoinsDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinsDto.toDomain(): CoinModel {
    return CoinModel(
        id = id,
        name = name,
        isActive = isActive,
        rank = rank,
        symbol = symbol
    )
}