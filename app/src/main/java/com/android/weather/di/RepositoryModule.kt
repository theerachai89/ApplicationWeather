package com.android.weather.di

import com.android.weather.data.weather.repository.WeatherRepository
import com.android.weather.data.weather.repository.WeatherRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<WeatherRepository>{WeatherRepositoryImpl(get())}
}
