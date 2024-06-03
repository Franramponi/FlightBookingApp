package com.edu.ort.tp3_belgrano_a_grupo4.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R

class WeeklyFlightHolder(v: View): RecyclerView.ViewHolder(v) {
    private var view: View
    init {
        this.view = v
    }
    fun setCountry(country : String){
        val txt: TextView = view.findViewById(R.id.pais_textView)
        txt.text = country
    }
    fun setPrice(price : Int){
        val simbolo = "$"
        val txt: TextView = view.findViewById(R.id.text_price)
        val retorno = simbolo + price.toString()
        txt.text = retorno
    }
    fun getCardLayout(): View{
        return view.findViewById(R.id.card_weekly)
    }
}