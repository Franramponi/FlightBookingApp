package com.edu.ort.tp3_belgrano_a_grupo4.entities

import android.os.Parcel
import android.os.Parcelable

data class TrendingDestination(
    val country: String = "",
    val airport: String = "",
    val flightId: String = "",
    val imgResource: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeString(airport)
        parcel.writeString(flightId)
        parcel.writeInt(imgResource)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrendingDestination> {
        override fun createFromParcel(parcel: Parcel): TrendingDestination {
            return TrendingDestination(parcel)
        }

        override fun newArray(size: Int): Array<TrendingDestination?> {
            return arrayOfNulls(size)
        }
    }
}
