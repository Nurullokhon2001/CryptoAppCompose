package com.example.cryptoappcompose.di

import com.example.cryptoappcompose.common.Constants.BASE_URL
import com.example.cryptoappcompose.data.remote.CoinPaprikaApi
import com.example.cryptoappcompose.data.repository.CoinRepositoryImpl
import com.example.cryptoappcompose.domain.repository.CoinRepository
import com.example.cryptoappcompose.domain.use_case.GetCoinDetailsUseCase
import com.example.cryptoappcompose.domain.use_case.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinPaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetCoinsUseCase(repo: CoinRepository) = GetCoinsUseCase(repo)

    @Provides
    @Singleton
    fun provideGetCoinDetailsUseCase(repo: CoinRepository) = GetCoinDetailsUseCase(repo)
}