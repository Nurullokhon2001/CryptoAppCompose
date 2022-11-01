package com.example.cryptoappcompose.domain.use_case

import com.example.cryptoappcompose.common.Resource
import com.example.cryptoappcompose.data.remote.dto.toDomain
import com.example.cryptoappcompose.domain.model.CoinModel
import com.example.cryptoappcompose.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<CoinModel>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toDomain() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}