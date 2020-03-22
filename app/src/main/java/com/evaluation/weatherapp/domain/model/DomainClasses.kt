package com.evaluation.weatherapp.domain.model

/**
 * @author Vladyslav Havrylenko
 * @since 22.03.2020
 */
data class ForecastList(
    val city: String,
    val country: String,
    val dailyForecast: List<Forecast>
)

data class Forecast(
    val date: String,
    val description: String,
    val high: Int,
    val low: Int
)