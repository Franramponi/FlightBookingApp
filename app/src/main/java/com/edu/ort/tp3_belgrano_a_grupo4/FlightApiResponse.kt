package com.edu.ort.tp3_belgrano_a_grupo4.adapters.com.edu.ort.tp3_belgrano_a_grupo4

data class ApiResponse(
    val search_metadata: SearchMetadata,
    val best_flights: List<Flight>
)

data class SearchMetadata(
    val status: String,
    // Agrega otros campos si los necesitas
)

data class Flight(
    val flights: List<FlightDetails>,
    // Agrega otros campos si los necesitas
)

data class FlightDetails(
    val departure_airport: Airport,
    val arrival_airport: Airport,
    val travel_class: String,
    val duration: Int,
    val price: Int
)

data class Airport(
    val name: String,
    val id: String,
    val time: String
)