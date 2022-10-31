package com.android.weather.data.weather.repository

import com.android.weather.constants.WeatherConstants
import com.android.weather.data.api.ApiInterface
import com.android.weather.data.weather.model.WeatherListResponse
import com.android.weather.data.weather.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface WeatherRepository {
    fun getWeather(
        city: String,
        units: String = WeatherConstants.Units.metric,
        lang: String = WeatherConstants.Lang.TH
    )
            : Flow<WeatherResponse>

    fun getWeatherList(
        city: String,
        units: String = WeatherConstants.Units.metric
    )
            : Flow<WeatherListResponse>
}

class WeatherRepositoryImpl(
    private val api: ApiInterface
) : WeatherRepository {

    companion object {
        private const val ERROR_LOAD = "Retrieving shelf is fail or data not found"
    }

    override fun getWeather(
        city: String,
        units: String,
        lang: String
    ): Flow<WeatherResponse> {
        return flow {
            val response = api.getWeather(city, units, lang)
            val result = if (response.isSuccessful &&
                response.body() != null &&
                response.code() == 200
            ) {

                response.body() ?: WeatherResponse()
            } else {
                error(ERROR_LOAD)
            }
            emit(result)
        }
    }

    override fun getWeatherList(city: String, units: String)
            : Flow<WeatherListResponse> {
            return flow {
                val response = api.getWeatherList(city, units)
                val result = if (response.isSuccessful &&
                    response.body() != null &&
                    response.code() == 200
                ) {

                    response.body() ?: WeatherListResponse()
                } else {
                    error(ERROR_LOAD)
                }
                emit(result)
            }
    }
}