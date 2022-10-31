package com.android.weather.domain

import com.android.weather.constants.WeatherConstants
import com.android.weather.data.weather.model.WeatherResponse
import com.android.weather.data.weather.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface WeatherUseCase {
    fun execute(city:String,
                units:String = WeatherConstants.Units.metric,
                lang:String = WeatherConstants.Lang.TH) : Flow<WeatherResponse>
}

class WeatherUseCaseImpl(
    private val repository: WeatherRepository
) : WeatherUseCase {

    override fun execute(city: String,
                         units:String,
                         lang:String): Flow<WeatherResponse> {
        return repository.getWeather(city,units,lang).map {
            it.units = units
            it
        }
    }
}