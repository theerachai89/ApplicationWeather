package com.android.weather.data.base

sealed class ResultResponse<out T : Any>

class Success<out T : Any>(val data: T) : ResultResponse<T>()

class Failure(val exception: Throwable) : ResultResponse<Nothing>()

class Progress(val isLoading: Boolean) : ResultResponse<Nothing>()
