package com.oliver.earthquake.data.network

import com.oliver.earthquake.data.model.EarthQuakeResponse
import retrofit2.Call
import retrofit2.http.GET

interface EarthquakeService {

    @GET("/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman")
    fun getEarthquakeData(): Call<EarthQuakeResponse>
}
