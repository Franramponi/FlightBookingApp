package com.edu.ort.tp3_belgrano_a_grupo4.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R
import com.edu.ort.tp3_belgrano_a_grupo4.entities.WeeklyFlight
import com.edu.ort.tp3_belgrano_a_grupo4.holders.WeeklyFlightHolder


class WeeklyFlightAdapter(
    private val weeklyflights : MutableList<WeeklyFlight>
) : RecyclerView.Adapter<WeeklyFlightHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyFlightHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.weekly_flight,parent,false)
        return (WeeklyFlightHolder(view))
    }

    override fun getItemCount()= weeklyflights.size

    override fun onBindViewHolder(holder: WeeklyFlightHolder, position: Int) {
       val weekly = weeklyflights[position]
        holder.setCountry(weekly.country)
        holder.setPrice(weekly.price)
    }

}