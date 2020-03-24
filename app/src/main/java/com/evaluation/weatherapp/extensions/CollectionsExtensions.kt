package com.evaluation.weatherapp.extensions

/**
 * @author Vladyslav Havrylenko
 * @since 24.03.2020
 */
fun <K, V : Any> Map<K, V?>.toVarargArray():
        Array<out Pair<K, V>> = map { Pair(it.key, it.value!!) }.toTypedArray()