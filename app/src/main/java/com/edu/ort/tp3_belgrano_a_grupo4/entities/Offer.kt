package com.edu.ort.tp3_belgrano_a_grupo4.entities

import android.os.Parcel
import android.os.Parcelable

data class Offer(
    val nombreTarjeta: String = "",
    val cardImg: Int = 0,
    val porcentajeDesc: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombreTarjeta)
        parcel.writeInt(cardImg)
        parcel.writeInt(porcentajeDesc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Offer> {
        override fun createFromParcel(parcel: Parcel): Offer {
            return Offer(parcel)
        }

        override fun newArray(size: Int): Array<Offer?> {
            return arrayOfNulls(size)
        }
    }
}

