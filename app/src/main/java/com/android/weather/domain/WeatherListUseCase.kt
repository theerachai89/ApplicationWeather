package com.android.weather.domain

import com.android.weather.constants.WeatherConstants
import com.android.weather.data.weather.model.WeatherListResponse
import com.android.weather.data.weather.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface WeatherListUseCase {
    fun execute(city:String,
                units:String = WeatherConstants.Units.metric) : Flow<WeatherListResponse>
}

class WeatherListUseCaseImpl(
    private val repository: WeatherRepository
) : WeatherListUseCase {

    override fun execute(city: String,
                         units:String): Flow<WeatherListResponse> {
        return repository.getWeatherList(city,units).map {
            it.units = units
            it
        }
    }
}