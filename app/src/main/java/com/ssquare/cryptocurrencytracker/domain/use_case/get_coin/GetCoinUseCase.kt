package com.ssquare.cryptocurrencytracker.domain.use_case.get_coin

import com.ssquare.cryptocurrencytracker.common.Resource
import com.ssquare.cryptocurrencytracker.data.remote.dto.toCoinDetail
import com.ssquare.cryptocurrencytracker.domain.model.CoinDetail
import com.ssquare.cryptocurrencytracker.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        }catch (e: HttpException){
            emit(Resource.Error<CoinDetail>(e.localizedMessage?: "An error occurred"))
        }catch (e: IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach the server. Please check your internet connection"))
        }
    }
}