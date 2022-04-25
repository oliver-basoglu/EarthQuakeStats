package com.oliver.earthquake.data.model

import java.lang.Error

sealed class EarthQuakeResponseResult {
    class Success(val earthQuakeResponse: EarthQuakeResponse) : EarthQuakeResponseResult()
    class Failure(val error: Error) : EarthQuakeResponseResult()
    object IsLoading : EarthQuakeResponseResult()
}
