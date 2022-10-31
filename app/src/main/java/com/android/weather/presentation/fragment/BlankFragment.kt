package com.android.weather.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.weather.R
import com.android.weather.constants.WeatherConstants
import com.android.weather.data.base.Progress
import com.android.weather.data.base.Success
import com.android.weather.data.weather.model.WeatherResponse
import com.android.weather.databinding.FragmentBlankBinding
import com.android.weather.extensions.gone
import com.android.weather.extensions.visible
import com.android.weather.presentation.activity.MainActivity
import com.bumptech.glide.Glide


class BlankFragment : Fragment() {

    private val activityMain by lazy { (requireActivity() as MainActivity) }
    private val binding: FragmentBlankBinding by lazy {
        FragmentBlankBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWeather(activityMain.weatherViewModel.keyword)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        search.onTextChange { keyword ->
            getWeather(keyword)
        }
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            setWeatherUnits(
                when (checkedId) {
                    R.id.rbMetric -> {
                        WeatherConstants.Units.metric
                    }
                    else -> {
                        WeatherConstants.Units.imperial
                    }
                }
            )
        }
    }

    private fun getWeather(keyword: String) {
        activityMain.weatherViewModel.search(keyword)
    }

    private fun setWeatherUnits(units: String) {
        activityMain.weatherViewModel.changeUnits(units)
    }

    private fun initViewModel() {
        with(activityMain.weatherViewModel) {
            getWeatherData().observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Success -> {
                        binding.progressBar.gone()
                        setView(result.data)
                    }
                    is Progress -> binding.progressBar.visible()
                    else -> binding.progressBar.gone()
                }

            }

            when(units){
                WeatherConstants.Units.metric -> binding.rbMetric.isChecked = true
                else -> binding.rbImperial.isChecked = true
            }
        }
    }

    private fun setView(result: WeatherResponse) {
        binding.apply {
            tvCity.text = result.name
            tvTemp.text = when (result.units) {
                WeatherConstants.Units.metric -> "${result.main?.temp?.toInt()} °C"
                else -> "${result.main?.temp?.toInt()} °F"
            }
            tvHumidity.text = "${result.main?.humidity}%"
            tvHumidityLabel.visible()

            Glide.with(requireContext())
                .load(getString(R.string.url_img, result.weather?.first()?.icon))
                .into(icon)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}