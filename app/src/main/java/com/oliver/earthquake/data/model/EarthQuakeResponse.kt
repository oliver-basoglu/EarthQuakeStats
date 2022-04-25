package com.oliver.earthquake.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EarthQuakeResponse(
	@field:SerializedName("earthquakes")
	val earthquakes: List<EarthquakeItem>? = null
) : Parcelable

@Parcelize
data class EarthquakeItem(
	@field:SerializedName("datetime")
	val dateTime: String? = null,

	val depth: Double? = null,

	@field:SerializedName("lng")
	val longitude: Double? = null,

	@field:SerializedName("src")
	val source: String? = null,

	@field:SerializedName("eqid")
	val eqId: String? = null,

	@field:SerializedName("magnitude")
	val magnitude: Double? = null,

	@field:SerializedName("lat")
	val latitude: Double? = null
) : Parcelable
