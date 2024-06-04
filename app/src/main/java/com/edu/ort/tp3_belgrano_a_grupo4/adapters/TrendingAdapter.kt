package com.edu.ort.tp3_belgrano_a_grupo4.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R
import com.edu.ort.tp3_belgrano_a_grupo4.entities.TrendingDestination
import com.edu.ort.tp3_belgrano_a_grupo4.entities.WeeklyFlight
import com.edu.ort.tp3_belgrano_a_grupo4.holders.TrendingHolder
import com.edu.ort.tp3_belgrano_a_grupo4.holders.WeeklyFlightHolder

class TrendingAdapter(private val trendings: MutableList<TrendingDestination>) : RecyclerView.Adapter<TrendingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trending_destination, parent, false)
        return TrendingHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingHolder, position: Int) {
        val trendingDestination = trendings[position]
        holder.setAirport(trendingDestination.airport)
        holder.setCountry(trendingDestination.country)
        holder.setFlightId(trendingDestination.flightId)
        holder.setImgTrending(trendingDestination.imgResource)
    }

    override fun getItemCount(): Int {
        return trendings.size
    }
}