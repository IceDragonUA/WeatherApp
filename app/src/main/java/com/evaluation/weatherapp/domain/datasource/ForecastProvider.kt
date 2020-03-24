package com.evaluation.weatherapp.domain.datasource

import com.evaluation.weatherapp.data.db.ForecastDb
import com.evaluation.weatherapp.data.server.ForecastServer
import com.evaluation.weatherapp.domain.model.Forecast
import com.evaluation.weatherapp.domain.model.ForecastList
import com.evaluation.weatherapp.extensions.firstResult

/**
 * @author Vladyslav Havrylenko
 * @since 24.03.2020
 */
class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}