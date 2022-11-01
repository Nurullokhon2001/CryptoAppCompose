package com.example.cryptoappcompose.data.repository

import com.example.cryptoappcompose.data.remote.CoinPaprikaApi
import com.example.cryptoappcompose.data.remote.dto.CoinDetailsDto
import com.example.cryptoappcompose.data.remote.dto.CoinsDto
import com.example.cryptoappcompose.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinsDto> {
        return coinPaprikaApi.getCoins()
    }

    override suspend fun getCoinDetails(coinId : String): CoinDetailsDto {
        return coinPaprikaApi.getCoinById(coinId)
    }
}