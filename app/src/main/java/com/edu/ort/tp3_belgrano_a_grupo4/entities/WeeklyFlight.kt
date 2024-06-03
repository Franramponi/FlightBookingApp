package com.edu.ort.tp3_belgrano_a_grupo4.entities

import android.os.Parcel
import android.os.Parcelable

data class WeeklyFlight(
    val country: String = "",
    val price: Int = 0,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
    )

    override fun toString(): String {
        return "WeeklyFlight(country='$country', price=$price)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeeklyFlight> {
        override fun createFromParcel(parcel: Parcel): WeeklyFlight {
            return WeeklyFlight(parcel)
        }

        override fun newArray(size: Int): Array<WeeklyFlight?> {
            return arrayOfNulls(size)
        }
    }
}