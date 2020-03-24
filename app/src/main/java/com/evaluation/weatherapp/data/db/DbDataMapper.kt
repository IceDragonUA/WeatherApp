package com.evaluation.weatherapp.data.db

import com.evaluation.weatherapp.domain.model.Forecast
import com.evaluation.weatherapp.domain.model.ForecastList

/**
 * @author Vladyslav Havrylenko
 * @since 24.03.2020
 */
class DbDataMapper {

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        CityForecast(id, city, country, dailyForecast.map { convertDayFromDomain(id, it) })
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        ForecastList(_id, city, country, dailyForecast.map { convertDayToDomain(it) })
    }

    private fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(date, description, high, low, iconUrl)
    }
}