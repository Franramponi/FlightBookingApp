package com.edu.ort.tp3_belgrano_a_grupo4.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R

class SearchFlightHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v

    fun setAirline(airline: String) {
        val txtAirline: TextView = view.findViewById(R.id.name_airline)
        txtAirline.text = airline
    }

    fun setDestination(name: String, id: String) {
        val txtDestination: TextView = view.findViewById(R.id.txt_Destination)
        val txtDestinationId: TextView = view.findViewById(R.id.id_Destination)
        txtDestination.text = name
        txtDestinationId.text = id
    }

    fun setOrigin(name: String, id: String) {
        val txtOrigin: TextView = view.findViewById(R.id.txt_Origin)
        val txtOriginId: TextView = view.findViewById(R.id.id_Origin)
        txtOrigin.text = name
        txtOriginId.text = id
    }

    fun setTravelClass(travelClass: String) {
        val txtTravelClass: TextView = view.findViewById(R.id.name_class)
        txtTravelClass.text = travelClass
    }

    fun setPrice(price: Int) {
        val txtPrice: TextView = view.findViewById(R.id.id_price)
        val priceMon = "$" + price.toString()
        txtPrice.text = priceMon
    }

    fun getCardLayout(): View {
        return view.findViewById(R.id.card_search)
    }
}