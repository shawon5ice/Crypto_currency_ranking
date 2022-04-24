package com.ssquare.cryptocurrencytracker.presentation.coin_details

import com.ssquare.cryptocurrencytracker.domain.model.CoinDetail

data class CoinDetailState (
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)