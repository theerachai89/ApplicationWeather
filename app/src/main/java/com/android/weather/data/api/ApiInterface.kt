package com.android.weather.data.api

import com.android.weather.data.weather.model.*
import retrofit2.http.*
import retrofit2.Response

interface ApiInterface {

    @GET("weather?appid=dcd41b0a4a0ef77c08eed35c0341b964")
    suspend fun getWeather(
        @Query("q") city:String,
        @Query("units") metric:String,
        @Query("lang") lang:String
    ): Response<WeatherResponse>

    @GET("forecast?appid=dcd41b0a4a0ef77c08eed35c0341b964&lang=th&cnt=8")
    suspend fun getWeatherList(
        @Query("q") city:String,
        @Query("units") metric:String
    ): Response<WeatherListResponse>
}