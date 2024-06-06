package com.edu.ort.tp3_belgrano_a_grupo4.entities

import android.os.Parcel
import android.os.Parcelable

data class DepartureAirport(
    val name: String,
    val id: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DepartureAirport> {
        override fun createFromParcel(parcel: Parcel): DepartureAirport {
            return DepartureAirport(parcel)
        }

        override fun newArray(size: Int): Array<DepartureAirport?> {
            return arrayOfNulls(size)
        }
    }
}

