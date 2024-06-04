package com.edu.ort.tp3_belgrano_a_grupo4.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R
import com.edu.ort.tp3_belgrano_a_grupo4.entities.Offer
import com.edu.ort.tp3_belgrano_a_grupo4.entities.TrendingDestination
import com.edu.ort.tp3_belgrano_a_grupo4.entities.WeeklyFlight
import com.edu.ort.tp3_belgrano_a_grupo4.holders.OfferHolder


class OfferAdapter(
    private val offers: MutableList<Offer>,
    private val clickListener: (Offer) -> Unit
) : RecyclerView.Adapter<OfferHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offer, parent, false)
        return OfferHolder(view)
    }

    override fun getItemCount() = offers.size

    override fun onBindViewHolder(holder: OfferHolder, position: Int) {
        val offer = offers[position]
        holder.setNombreTarjeta(offer.nombreTarjeta)
        holder.setCardImg(offer.cardImg)

        holder.getCardLayout().setOnClickListener {
            clickListener(offer)
        }
    }


}