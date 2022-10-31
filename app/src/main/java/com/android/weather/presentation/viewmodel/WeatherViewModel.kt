package com.android.weather.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weather.constants.WeatherConstants
import com.android.weather.data.base.*
import com.android.weather.data.weather.model.WeatherListResponse
import com.android.weather.data.weather.model.WeatherResponse
import com.android.weather.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val weatherListUseCase: WeatherListUseCase
) : ViewModel() {

    private val weatherResponse = MutableLiveData<ResultResponse<WeatherResponse>>()
    fun getWeatherData() = weatherResponse
    private val weatherListResponse = MutableLiveData<ResultResponse<WeatherListResponse>>()
    fun getWeatherListData() = weatherListResponse
    private var searchJob: Job? = null

    var keyword:String = "Bangkok"
    var units:String = WeatherConstants.Units.metric

    private fun getWeather(city:String,
                           units:String = WeatherConstants.Units.metric) {
        viewModelScope.launch {
            weatherUseCase.execute(city,units)
                .flowOn(Dispatchers.IO)
                .catch { exception ->
                    weatherResponse.value = Failure(exception = exception)
                }
                .onStart {
                    weatherResponse.value = Progress(true)
                }
                .flowOn(Dispatchers.Main)
                .collect {
                    weatherResponse.value = Success(it)
                }
        }
    }


    fun search(keyword: String) {
        this.keyword = keyword
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(100)
            getWeather(keyword,units)
        }
    }

    fun changeUnits(units: String) {
        this.units = units
        getWeather(keyword,units)
    }


    fun getWeatherList() {
        viewModelScope.launch {
            weatherListUseCase.execute(keyword,units)
                .flowOn(Dispatchers.IO)
                .catch { exception ->
                    weatherListResponse.value = Failure(exception = exception)
                }
                .onStart {
                    weatherListResponse.value = Progress(true)
                }
                .flowOn(Dispatchers.Main)
                .collect {
                    weatherListResponse.value = Success(it)
                }
        }
    }

}