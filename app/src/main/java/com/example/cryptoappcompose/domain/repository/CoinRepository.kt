package com.example.cryptoappcompose.domain.repository

import com.example.cryptoappcompose.data.remote.dto.CoinDetailsDto
import com.example.cryptoappcompose.data.remote.dto.CoinsDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinsDto>

    suspend fun getCoinDetails(coinId : String): CoinDetailsDto
}