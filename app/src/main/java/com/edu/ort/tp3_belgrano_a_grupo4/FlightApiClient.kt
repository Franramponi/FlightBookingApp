package com.edu.ort.tp3_belgrano_a_grupo4.adapters.com.edu.ort.tp3_belgrano_a_grupo4

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FlightApiClient {
    private const val BASE_URL = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/search?engine=google_flights&api_key=123&departure_id=EZE&arrival_id=MIA&outbound_date=2024-05-31&return_date=2024-06-10"

    fun create(): FlightService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(FlightService::class.java)
    }
}
