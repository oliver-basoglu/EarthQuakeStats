package com.oliver.earthquake.data.repositories

import com.oliver.earthquake.API_FETCHING_ERROR
import com.oliver.earthquake.data.model.EarthQuakeResponseResult
import com.oliver.earthquake.data.network.EarthquakeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Error
import javax.inject.Inject

class EarthquakeRepository @Inject constructor(
    private val earthquakeService: EarthquakeService
) : EarthquakeRepositoryImp {

    override suspend fun fetchEarthQuakeData(): EarthQuakeResponseResult =
        withContext(Dispatchers.IO) {
            val earthquakeCall = earthquakeService.getEarthquakeData()
            try {
                val response = earthquakeCall.execute()
                val earthQuakeResponse = response.body()

                earthQuakeResponse?.let {
                    return@withContext EarthQuakeResponseResult.Success(it)
                } ?: run {
                    return@withContext EarthQuakeResponseResult.Failure(Error(API_FETCHING_ERROR))
                }
            } catch (e: Exception) {
                return@withContext EarthQuakeResponseResult.Failure(
                    Error(e.localizedMessage, null)
                )
            }
        }
}
