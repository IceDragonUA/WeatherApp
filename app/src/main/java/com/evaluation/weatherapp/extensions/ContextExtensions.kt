package com.evaluation.weatherapp.extensions

import android.content.Context
import androidx.core.content.ContextCompat

/**
 * @author Vladyslav Havrylenko
 * @since 24.03.2020
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)