package com.example.weatherapp.model.pojos.response.forecast

import com.example.weatherapp.model.pojos.local.forecast.WeatherForecast
import com.example.weatherapp.model.pojos.response.weather.Clouds
import com.example.weatherapp.model.pojos.response.weather.Main
import com.example.weatherapp.model.pojos.response.weather.Weather
import com.example.weatherapp.model.pojos.response.weather.Wind

data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val dt_txt: String
)

fun ForecastItem.toWeatherForecast(): WeatherForecast{
    return WeatherForecast(
        temp = main.temp.toInt(),
        isFav = false,
        cityId = 0,
        dt = dt_txt,
        icon = weather[0].icon
    )
}