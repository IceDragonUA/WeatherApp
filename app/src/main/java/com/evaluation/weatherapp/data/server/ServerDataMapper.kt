package com.evaluation.weatherapp.data.server

import com.evaluation.weatherapp.domain.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import com.evaluation.weatherapp.domain.model.Forecast as ModelForecast

/**
 * @author Vladyslav Havrylenko
 * @since 22.03.2020
 */
class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(
            id,
            dt,
            weather[0].description,
            temp.max.toInt(),
            temp.min.toInt(),
            generateIconUrl(weather[0].icon)
        )
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}