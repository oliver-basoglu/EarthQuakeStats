package com.oliver.earthquake.data.di

import com.oliver.earthquake.data.network.EarthquakeService
import com.oliver.earthquake.BASE_URL
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

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Singleton
    @Provides
    fun provideEarthquakeService(retrofit: Retrofit): EarthquakeService =
        retrofit.create(EarthquakeService::class.java)
}
