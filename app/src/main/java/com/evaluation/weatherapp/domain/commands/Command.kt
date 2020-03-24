package com.evaluation.weatherapp.domain.commands

/**
 * @author Vladyslav Havrylenko
 * @since 22.03.2020
 */
interface Command<out T> {
    fun execute(): T
}