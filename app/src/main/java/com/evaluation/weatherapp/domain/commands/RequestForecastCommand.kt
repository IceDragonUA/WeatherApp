package com.evaluation.weatherapp.domain.commands

import com.evaluation.weatherapp.domain.datasource.ForecastProvider
import com.evaluation.weatherapp.domain.model.ForecastList

/**
 * @author Vladyslav Havrylenko
 * @since 22.03.2020
 */
class RequestForecastCommand(
    private val zipCode: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()
) :
    Command<ForecastList> {

    companion object {
        const val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}