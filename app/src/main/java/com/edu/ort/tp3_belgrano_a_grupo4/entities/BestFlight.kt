package com.edu.ort.tp3_belgrano_a_grupo4.entities

import android.os.Parcel
import android.os.Parcelable

data class BestFlight(
    val flights: List<Flight>,
    val price: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Flight.CREATOR) ?: emptyList(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(flights)
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BestFlight> {
        override fun createFromParcel(parcel: Parcel): BestFlight {
            return BestFlight(parcel)
        }

        override fun newArray(size: Int): Array<BestFlight?> {
            return arrayOfNulls(size)
        }
    }
}
