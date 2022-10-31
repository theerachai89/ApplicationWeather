package com.android.weather.di

import com.android.weather.domain.*
import org.koin.dsl.module

val useCaseModule = module {
    factory<WeatherUseCase> { WeatherUseCaseImpl(get()) }
    factory<WeatherListUseCase> { WeatherListUseCaseImpl(get()) }
}