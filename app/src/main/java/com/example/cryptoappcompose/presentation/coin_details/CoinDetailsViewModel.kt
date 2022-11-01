package com.example.cryptoappcompose.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoappcompose.common.Constants.PARAMS_COIN_ID
import com.example.cryptoappcompose.common.Resource
import com.example.cryptoappcompose.domain.use_case.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    init {
        savedStateHandle.get<String>(PARAMS_COIN_ID)?.let { coinId ->
            getCoinDetails(coinId)
        }
    }

    private fun getCoinDetails(coinId: String) {
        getCoinDetailsUseCase.invoke(coinId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _state.value = CoinDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinDetailsState(data = it.data)
                }
                is Resource.Error -> {
                    _state.value =
                        CoinDetailsState(error = it.message ?: "An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }
}