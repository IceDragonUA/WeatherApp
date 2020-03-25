package com.evaluation.weatherapp.ui.activities

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.evaluation.weatherapp.R
import com.evaluation.weatherapp.domain.commands.RequestForecastCommand
import com.evaluation.weatherapp.extensions.DelegatesExt
import com.evaluation.weatherapp.ui.activities.SettingsActivity.Companion.DEFAULT_ZIP
import com.evaluation.weatherapp.ui.activities.SettingsActivity.Companion.ZIP_CODE
import com.evaluation.weatherapp.ui.adapter.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    val zipCode: Long by DelegatesExt.preference(this, ZIP_CODE, DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync {
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {
            val adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(
                    DetailActivity.ID to it.id,
                    DetailActivity.CITY_NAME to result.city
                )
            }
            forecastList.adapter = adapter
            title = "${result.city} (${result.country})"
        }
    }

}
