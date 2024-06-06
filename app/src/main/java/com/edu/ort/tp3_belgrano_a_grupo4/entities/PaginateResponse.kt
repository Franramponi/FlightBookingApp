package com.edu.ort.tp3_belgrano_a_grupo4.entities

import android.os.Parcel
import android.os.Parcelable

data class PaginateResponse(
    val best_flights: List<BestFlight>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(BestFlight.CREATOR) ?: emptyList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(best_flights)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PaginateResponse> {
        override fun createFromParcel(parcel: Parcel): PaginateResponse {
            return PaginateResponse(parcel)
        }

        override fun newArray(size: Int): Array<PaginateResponse?> {
            return arrayOfNulls(size)
        }
    }
}

private fun Parcel.writeTypedList(bestFlights: List<BestFlight>) {
    TODO("Not yet implemented")
}
