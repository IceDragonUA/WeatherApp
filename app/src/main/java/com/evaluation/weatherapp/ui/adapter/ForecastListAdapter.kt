package com.evaluation.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.weatherapp.R
import com.evaluation.weatherapp.domain.model.Forecast
import com.evaluation.weatherapp.domain.model.ForecastList
import com.evaluation.weatherapp.utils.ctx
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * @author Vladyslav Havrylenko
 * @since 04.09.2019
 */
class ForecastListAdapter(
    private val weekForecast: ForecastList,
    private val itemClick: OnItemClickListener
) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false), itemClick)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(view: View, val itemClick: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {

        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature)

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx)
                    .load(iconUrl)
                    .into(iconView)

                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"

                itemView.setOnClickListener { itemClick(this) }

            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast);
    }
}