package com.oliver.earthquake.data.repositories

import com.oliver.earthquake.data.model.EarthQuakeResponseResult

interface EarthquakeRepositoryImp {
    suspend fun fetchEarthQuakeData() : EarthQuakeResponseResult
}
