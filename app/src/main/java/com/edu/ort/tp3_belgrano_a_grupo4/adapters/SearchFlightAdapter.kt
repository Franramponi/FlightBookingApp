package com.edu.ort.tp3_belgrano_a_grupo4.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R
import com.edu.ort.tp3_belgrano_a_grupo4.entities.BestFlight
import com.edu.ort.tp3_belgrano_a_grupo4.holders.SearchFlightHolder

class SearchFlightAdapter(private var flights: MutableList<BestFlight>) : RecyclerView.Adapter<SearchFlightHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFlightHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_search_result, parent, false)
        return SearchFlightHolder(view)
    }

    override fun onBindViewHolder(holder: SearchFlightHolder, position: Int) {
        val bestFlight = flights[position]
        val firstFlight = bestFlight.flights.firstOrNull()

        if (firstFlight != null) {
            holder.setAirline(firstFlight.airline)
            holder.setDestination(firstFlight.arrival_airport.name, firstFlight.arrival_airport.id)
            holder.setOrigin(firstFlight.departure_airport.name, firstFlight.departure_airport.id)
            holder.setTravelClass(firstFlight.travel_class)
            holder.setPrice(bestFlight.price)
        }

        holder.getCardLayout().setOnClickListener {
            // Handle click event
        }
    }

    override fun getItemCount(): Int {
        return flights.size
    }
}