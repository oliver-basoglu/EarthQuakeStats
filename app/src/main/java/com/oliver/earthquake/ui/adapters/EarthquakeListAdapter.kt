package com.oliver.earthquake.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.oliver.earthquake.R
import com.oliver.earthquake.data.model.EarthquakeItem
import com.oliver.earthquake.databinding.EarthquakeListItemBinding

class EarthquakeListAdapter(var earthquakeList: List<EarthquakeItem>) :
    RecyclerView.Adapter<EarthquakeListAdapter.EarthquakeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        return EarthquakeViewHolder(
            EarthquakeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        val earthquakeItem = earthquakeList[position]
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(earthquakeItem) }
            }
        }
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return earthquakeList.size
    }

    inner class EarthquakeViewHolder(private val itemBinding: EarthquakeListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int) {
            itemView.apply {
                val magnitude = earthquakeList[position].magnitude
                itemBinding.earthquakeDate.text = earthquakeList[position].dateTime
                itemBinding.earthquakeMagnitude.text = magnitude?.toString()
                itemBinding.earthquakeLatLong.text = resources.getString(R.string.lat_lng)
                    .plus(earthquakeList[position].latitude)
                    .plus(" , ")
                    .plus(earthquakeList[position].longitude)
                if (magnitude != null && magnitude > 8.0) {
                    itemBinding.earthquakeLayout.setBackgroundColor(getColor(context, R.color.warning))
                } else {
                    itemBinding.earthquakeLayout.setBackgroundColor(getColor(context, R.color.white))
                }
            }
        }
    }

    private var onItemClickListener: ((EarthquakeItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (EarthquakeItem) -> Unit) {
        onItemClickListener = listener
    }
}