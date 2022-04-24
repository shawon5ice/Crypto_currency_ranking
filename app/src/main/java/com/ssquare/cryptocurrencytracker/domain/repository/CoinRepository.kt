//This domain level repository just contain interface
// where data level repository contains the implementation of these unimplemented functions.

package com.ssquare.cryptocurrencytracker.domain.repository

import com.ssquare.cryptocurrencytracker.data.remote.dto.CoinDetailDto
import com.ssquare.cryptocurrencytracker.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}