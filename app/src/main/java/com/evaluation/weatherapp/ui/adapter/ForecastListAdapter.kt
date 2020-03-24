package com.evaluation.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.weatherapp.R
import com.evaluation.weatherapp.domain.model.Forecast
import com.evaluation.weatherapp.domain.model.ForecastList
import com.evaluation.weatherapp.extensions.ctx
import com.evaluation.weatherapp.extensions.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.DateFormat
import java.util.*

/**
 * @author Vladyslav Havrylenko
 * @since 04.09.2019
 */
class ForecastListAdapter(
    private val weekForecast: ForecastList,
    private val itemClick: (Forecast) -> Unit
) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false), itemClick)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) :
        RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx)
                    .load(iconUrl)
                    .into(itemView.icon)

                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "$high"
                itemView.minTemperature.text = "$low"

                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}