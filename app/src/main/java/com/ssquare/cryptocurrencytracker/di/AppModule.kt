package com.ssquare.cryptocurrencytracker.di

import com.ssquare.cryptocurrencytracker.common.Constants
import com.ssquare.cryptocurrencytracker.data.remote.CoinPaprikaApi
import com.ssquare.cryptocurrencytracker.data.repository.CoinRepositoryImp
import com.ssquare.cryptocurrencytracker.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: CoinPaprikaApi): CoinRepository{
        return CoinRepositoryImp(api)
    }
}