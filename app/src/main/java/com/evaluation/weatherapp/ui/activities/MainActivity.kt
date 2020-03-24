package com.evaluation.weatherapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.evaluation.weatherapp.R
import com.evaluation.weatherapp.domain.commands.RequestForecastCommand
import com.evaluation.weatherapp.ui.adapter.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) { toast(it.description) }
                forecastList.adapter = adapter
            }
        }
    }
}
