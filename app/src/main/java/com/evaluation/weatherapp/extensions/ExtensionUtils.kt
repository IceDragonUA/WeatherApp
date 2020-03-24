package com.evaluation.weatherapp.extensions

import java.text.DateFormat
import java.util.*

/**
 * @author Vladyslav Havrylenko
 * @since 24.03.2020
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}