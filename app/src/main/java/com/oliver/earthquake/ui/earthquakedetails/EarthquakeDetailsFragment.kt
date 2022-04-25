package com.oliver.earthquake.ui.earthquakedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.oliver.earthquake.R
import com.oliver.earthquake.databinding.FragmentEarthquakeDetailsBinding

class EarthquakeDetailsFragment : Fragment(R.layout.fragment_earthquake_details) {

    private var _binding: FragmentEarthquakeDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: EarthquakeDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataForFragmentDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthquakeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getDataForFragmentDetails() {
        val earthquake = args.argsEarthquakeItem
        binding.textDate.text = earthquake.dateTime.toString()
        binding.textDepth.text = earthquake.depth.toString()
        binding.textLatLong.text = resources.getString(R.string.lat_lng)
            .plus(earthquake.latitude)
            .plus(" , ")
            .plus(earthquake.longitude)
        binding.textSource.text = earthquake.source.toString()
        binding.textMagnitude.text = earthquake.magnitude.toString()
    }
}