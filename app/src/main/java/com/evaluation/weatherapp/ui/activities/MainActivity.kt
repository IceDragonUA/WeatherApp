package com.evaluation.weatherapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.weatherapp.R
import com.evaluation.weatherapp.data.ForecastRequest
import com.evaluation.weatherapp.domain.commands.RequestForecastCommand
import com.evaluation.weatherapp.ui.adapter.ForecastListAdapter
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val forecastList: RecyclerView = find(R.id.forecast_list)

        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result)
            }
        }
    }
}
