package com.evaluation.weatherapp.ui

import android.app.Application
import com.evaluation.weatherapp.extensions.DelegatesExt

/**
 * @author Vladyslav Havrylenko
 * @since 23.03.2020
 */
class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}