package com.oliver.earthquake

const val API_FETCHING_ERROR = "Could not fetch the data"

//Will only work on debug build due to overwriting the security setting
//In order to work on release builds, it has to be https instead of http
const val BASE_URL = "http://api.geonames.org"
