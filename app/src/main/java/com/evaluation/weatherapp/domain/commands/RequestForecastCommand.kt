package com.evaluation.weatherapp.domain.commands

import com.evaluation.weatherapp.data.server.ForecastRequest
import com.evaluation.weatherapp.domain.mappers.ForecastDataMapper
import com.evaluation.weatherapp.domain.model.ForecastList

/**
 * @author Vladyslav Havrylenko
 * @since 22.03.2020
 */
class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.execute())
    }

}