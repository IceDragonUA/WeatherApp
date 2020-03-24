package com.evaluation.weatherapp.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 * @author Vladyslav Havrylenko
 * @since 22.03.2020
 */
val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)