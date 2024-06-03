package com.edu.ort.tp3_belgrano_a_grupo4.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R
import org.intellij.lang.annotations.Identifier

class OfferHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    val mastercard = "Mastercard"

    fun setNombreTarjeta(name: String) {
        var info :String
        if (name==mastercard){
            info = "20% discount for " + name + " users"
        }else{
            info = "25% discount with your "+ name + " credit cards"
        }
        val txt: TextView = view.findViewById(R.id.txt_name)
        txt.text = info
    }

    fun setCardImg(imgResource: Int) {
        val imgView: ImageView = view.findViewById(R.id.cardImg)
        imgView.setImageResource(imgResource)
    }

    fun getCardLayout(): View {
        return view.findViewById(R.id.card_offer)
    }
}