package com.ssquare.cryptocurrencytracker.data.repository

import com.ssquare.cryptocurrencytracker.data.remote.CoinPaprikaApi
import com.ssquare.cryptocurrencytracker.data.remote.dto.CoinDetailDto
import com.ssquare.cryptocurrencytracker.data.remote.dto.CoinDto
import com.ssquare.cryptocurrencytracker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImp @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}