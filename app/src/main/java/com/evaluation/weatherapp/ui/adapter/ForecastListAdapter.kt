package com.evaluation.weatherapp.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.weatherapp.domain.model.Forecast
import com.evaluation.weatherapp.domain.model.ForecastList

/**
 * @author Vladyslav Havrylenko
 * @since 04.09.2019
 */
class ForecastListAdapter(private val weekForecast: ForecastList) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weekForecast.dailyForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.dailyForecast.size

    class ViewHolder(private val textView: TextView) : RecyclerView.ViewHolder(textView) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Forecast) {
            textView.text = "$item.date - $item.description - $item.high/$item.low"
        }
    }
}