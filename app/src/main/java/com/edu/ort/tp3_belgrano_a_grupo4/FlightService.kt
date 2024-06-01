package com.edu.ort.tp3_belgrano_a_grupo4.adapters.com.edu.ort.tp3_belgrano_a_grupo4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightService {
    @GET("search")
    fun searchFlights(
        @Query("engine") engine: String,
        @Query("api_key") apiKey: String,
        @Query("departure_id") departureId: String,
        @Query("arrival_id") arrivalId: String,
        @Query("outbound_date") outboundDate: String,
        @Query("return_date") returnDate: String
    ): Call<ApiResponse>
}
