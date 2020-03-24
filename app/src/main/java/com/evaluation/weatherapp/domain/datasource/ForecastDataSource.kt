package com.evaluation.weatherapp.domain.datasource

import com.evaluation.weatherapp.domain.model.Forecast
import com.evaluation.weatherapp.domain.model.ForecastList

/**
 * @author Vladyslav Havrylenko
 * @since 24.03.2020
 */
interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?

}