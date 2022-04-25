package com.oliver.earthquake.ui.earthquakelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliver.earthquake.data.model.EarthQuakeResponseResult
import com.oliver.earthquake.data.repositories.EarthquakeRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EarthquakeViewModel @Inject constructor(
    private var repository: EarthquakeRepositoryImp
) : ViewModel() {

    private var _earthquakeResponseResultLiveData = MutableLiveData<EarthQuakeResponseResult>()
    val earthQuakeResponseResultLiveData: LiveData<EarthQuakeResponseResult>
        get() = _earthquakeResponseResultLiveData

    init {
        fetchEarthquakeData()
    }

    fun fetchEarthquakeData() {
        Timber.d("fetching data")
        _earthquakeResponseResultLiveData.value = EarthQuakeResponseResult.IsLoading
        viewModelScope.launch {
            _earthquakeResponseResultLiveData.value = repository.fetchEarthQuakeData()
        }
    }
}
