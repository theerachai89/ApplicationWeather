package com.android.weather.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.weather.data.base.Progress
import com.android.weather.data.base.Success
import com.android.weather.data.weather.model.WeatherListResponse
import com.android.weather.databinding.FragmentListBinding
import com.android.weather.extensions.gone
import com.android.weather.extensions.toDateTime
import com.android.weather.extensions.visible
import com.android.weather.presentation.activity.MainActivity
import com.android.weather.presentation.adapter.WeatherAdapter

class ListFragment : Fragment() {

    private val activityMain by lazy { (requireContext()) as MainActivity }
    private lateinit var binding : FragmentListBinding
    private var adapterWeather = WeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
        initViewModel()
    }

    private fun initViewModel() {
        with(activityMain.weatherViewModel) {
            getWeatherListData().observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Success -> {
                        binding.progressBar.gone()
                        setView(result.data)
                    }
                    is Progress -> binding.progressBar.visible()
                    else -> binding.progressBar.gone()
                }
            }
        }
    }

    private fun setView(data: WeatherListResponse) = with(binding) {
        tvTitle.text = data.city?.name
        tvDatetime.text = data.list?.first()?.dtTxt?.toDateTime()
        adapterWeather.units = data.units
        data.list?.let { adapterWeather.setListData(it) }

    }

    private fun initView() = with(binding) {
        weatherRecyclerView.apply {
            adapter = adapterWeather
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun loadData() {
        with(activityMain) {
            weatherViewModel.getWeatherList()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}