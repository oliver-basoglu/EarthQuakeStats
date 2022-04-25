package com.oliver.earthquake.ui.earthquakelist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oliver.earthquake.ui.adapters.EarthquakeListAdapter
import com.oliver.earthquake.R
import com.oliver.earthquake.data.model.EarthQuakeResponseResult
import com.oliver.earthquake.data.model.EarthquakeItem
import com.oliver.earthquake.databinding.FragmentEarthquakeListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EarthquakeListFragment : Fragment(R.layout.fragment_earthquake_list) {

    private var _binding: FragmentEarthquakeListBinding? = null
    private val binding get() = _binding!!

    private val earthquakeViewModel: EarthquakeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        earthquakeViewModel.earthQuakeResponseResultLiveData.observe(
            viewLifecycleOwner,
            { earthQuakeResponseResult ->
                earthQuakeResponseResult?.let { it ->
                    when (it) {
                        is EarthQuakeResponseResult.Success -> {
                            it.earthQuakeResponse.earthquakes?.let { earthquakeList ->
                                buildEarthquakeRecycler(earthquakeList)
                            }
                        }
                        is EarthQuakeResponseResult.Failure -> {
                            Timber.d(it.error.localizedMessage)
                            Toast.makeText(
                                context,
                                getString(R.string.api_loading_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is EarthQuakeResponseResult.IsLoading -> {
                            Toast.makeText(
                                context,
                                getString(R.string.data_loading_message),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthquakeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun buildEarthquakeRecycler(earthquakeList: List<EarthquakeItem>) {
        val earthquakeListAdapter = EarthquakeListAdapter(earthquakeList)

        binding.recyclerViewEarthquake.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = earthquakeListAdapter
        }

        earthquakeListAdapter.setOnItemClickListener {
            val direction =
                EarthquakeListFragmentDirections.actionEarthquakeListFragmentToEarthquakeDetailsFragment(
                    it
                )
            findNavController().navigate(direction)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}