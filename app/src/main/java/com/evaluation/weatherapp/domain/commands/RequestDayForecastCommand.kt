package com.evaluation.weatherapp.domain.commands

import com.evaluation.weatherapp.domain.datasource.ForecastProvider
import com.evaluation.weatherapp.domain.model.Forecast

/**
 * @author Vladyslav Havrylenko
 * @since 24.03.2020
 */
class RequestDayForecastCommand(
    val id: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()) :
    Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}