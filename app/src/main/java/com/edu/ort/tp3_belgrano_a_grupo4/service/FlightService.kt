package com.edu.ort.tp3_belgrano_a_grupo4.service

import com.edu.ort.tp3_belgrano_a_grupo4.entities.Flight
import com.edu.ort.tp3_belgrano_a_grupo4.entities.PaginateResponse
import retrofit2.Call
import retrofit2.http.GET

interface FlightService {
    @GET("https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/search?engine=google_flights&api_key=123&departure_id=EZE&arrival_id=MIA&outbound_date=2024-05-31&return_date=2024-06-10\n")
    fun getFlight(): Call<PaginateResponse>
}