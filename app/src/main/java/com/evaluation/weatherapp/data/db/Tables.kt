package com.evaluation.weatherapp.data.db

/**
 * @author Vladyslav Havrylenko
 * @since 23.03.2020
 */
object CityForecastTable {
    const val NAME = "CityForecast"
    const val ID = "_id"
    const val CITY = "city"
    const val COUNTRY = "country"
}

object DayForecastTable {
    const val NAME = "DayForecast"
    const val ID = "_id"
    const val DATE = "date"
    const val DESCRIPTION = "description"
    const val HIGH = "high"
    const val LOW = "low"
    const val ICON_URL = "iconUrl"
    const val CITY_ID = "cityId"
}