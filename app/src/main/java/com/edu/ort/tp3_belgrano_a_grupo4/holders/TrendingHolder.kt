package com.edu.ort.tp3_belgrano_a_grupo4.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R

class TrendingHolder(v: View): RecyclerView.ViewHolder(v) {
    private var view: View = v

    fun setAirport(airport: String) {
        val txt: TextView = view.findViewById(R.id.txt_airport)
        txt.text = airport
    }

    fun setCountry(country: String) {
        val txt: TextView = view.findViewById(R.id.txt_country)
        txt.text = country
    }

    fun setFlightId(flightId: String) {
        val txt: TextView = view.findViewById(R.id.txt_id)
        txt.text = flightId
    }

    fun setImgTrending(imgTrending: Int) {
        val imgView: ImageView = view.findViewById(R.id.img_trending)
        imgView.setImageResource(imgTrending)
    }

    fun getCardLayout(): View {
        return view.findViewById(R.id.card_trending)
    }
}