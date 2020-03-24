package com.evaluation.weatherapp.data.db

import android.text.method.TextKeyListener.clear
import com.evaluation.weatherapp.domain.model.ForecastList
import com.evaluation.weatherapp.extensions.clear
import com.evaluation.weatherapp.extensions.parseList
import com.evaluation.weatherapp.extensions.parseOpt
import com.evaluation.weatherapp.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * @author Vladyslav Havrylenko
 * @since 24.03.2020
 */
class ForecastDb(
    private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
    private val dataMapper: DbDataMapper = DbDataMapper()
) {

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
            .whereSimple(dailyRequest, zipCode.toString(), date.toString())
            .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
            .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
            .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {

        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }
}