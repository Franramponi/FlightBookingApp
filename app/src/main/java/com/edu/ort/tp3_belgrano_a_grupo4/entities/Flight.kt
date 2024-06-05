package com.edu.ort.tp3_belgrano_a_grupo4.entities

import android.os.Parcel
import android.os.Parcelable

data class Flight(
    val airline: String,
    val arrival_airport: ArrivalAirport,
    val departure_airport: DepartureAirport,
    val duration: Int,
    val travel_class: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readParcelable(ArrivalAirport::class.java.classLoader) ?: ArrivalAirport("", ""),
        parcel.readParcelable(DepartureAirport::class.java.classLoader) ?: DepartureAirport("", ""),
        parcel.readInt(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(airline)
        parcel.writeParcelable(arrival_airport, flags)
        parcel.writeParcelable(departure_airport, flags)
        parcel.writeInt(duration)
        parcel.writeString(travel_class)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Flight> {
        override fun createFromParcel(parcel: Parcel): Flight {
            return Flight(parcel)
        }

        override fun newArray(size: Int): Array<Flight?> {
            return arrayOfNulls(size)
        }
    }
}
