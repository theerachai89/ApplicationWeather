package com.android.weather.data.weather.model

import com.android.weather.constants.WeatherConstants
import com.google.gson.annotations.SerializedName

data class WeatherListResponse(

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("cnt")
	val cnt: String? = null,

	@field:SerializedName("cod")
	val cod: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("list")
	val list: List<ListItem?>? = null,

	var units: String = WeatherConstants.Units.metric
)

data class _Wind(

	@field:SerializedName("deg")
	val deg: String? = null,

	@field:SerializedName("speed")
	val speed: Double? = null,

	@field:SerializedName("gust")
	val gust: Double? = null
)

data class City(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("coord")
	val coord: _Coord? = null,

	@field:SerializedName("sunrise")
	val sunrise: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("sunset")
	val sunset: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("population")
	val population: String? = null
)

data class _Mains(

	@field:SerializedName("temp")
	val temp: Double? = null,

	@field:SerializedName("temp_min")
	val tempMin: Double? = null,

	@field:SerializedName("grnd_level")
	val grndLevel: String? = null,

	@field:SerializedName("temp_kf")
	val tempKf: Double? = null,

	@field:SerializedName("humidity")
	val humidity: String? = null,

	@field:SerializedName("pressure")
	val pressure: String? = null,

	@field:SerializedName("sea_level")
	val seaLevel: String? = null,

	@field:SerializedName("feels_like")
	val feelsLike: Double? = null,

	@field:SerializedName("temp_max")
	val tempMax: Double? = null
)

data class _Clouds(

	@field:SerializedName("all")
	val all: String? = null
)

data class _Coord(

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)

data class ListItem(

	@field:SerializedName("dt")
	val dt: String? = null,

	@field:SerializedName("pop")
	val pop: String? = null,

	@field:SerializedName("visibility")
	val visibility: String? = null,

	@field:SerializedName("dt_txt")
	val dtTxt: String? = null,

	@field:SerializedName("weather")
	val weather: List<_WeatherItem?>? = null,

	@field:SerializedName("main")
	val main: _Mains? = null,

	@field:SerializedName("clouds")
	val clouds: _Clouds? = null,

	@field:SerializedName("sys")
	val sys: _Sys? = null,

	@field:SerializedName("wind")
	val wind: _Wind? = null
)

data class _WeatherItem(

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("main")
	val main: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class _Sys(

	@field:SerializedName("pod")
	val pod: String? = null
)
