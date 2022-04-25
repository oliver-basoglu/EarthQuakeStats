package com.oliver.earthquake.data.di

import com.oliver.earthquake.data.network.EarthquakeService
import com.oliver.earthquake.data.repositories.EarthquakeRepository
import com.oliver.earthquake.data.repositories.EarthquakeRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideEarthquakeRepository(earthquakeService: EarthquakeService) : EarthquakeRepositoryImp {
        return EarthquakeRepository(earthquakeService)
    }
}
