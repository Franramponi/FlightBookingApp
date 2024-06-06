package com.edu.ort.tp3_belgrano_a_grupo4.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R
import com.edu.ort.tp3_belgrano_a_grupo4.adapters.SearchFlightAdapter
import com.edu.ort.tp3_belgrano_a_grupo4.entities.PaginateResponse
import com.edu.ort.tp3_belgrano_a_grupo4.service.ActivityServiceApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResult : Fragment() {
    private lateinit var viewSearchResult: View
    private lateinit var recFlight: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewSearchResult = inflater.inflate(R.layout.fragment_search_result, container, false)
        recFlight = viewSearchResult.findViewById(R.id.recyclerView_search)
        return viewSearchResult
    }

    override fun onStart() {
        super.onStart()
        // Llama a la función loadFlight() para cargar los vuelos cuando el fragmento se vuelva visible
        loadFlight()
    }

    private fun loadFlight() {
        val service = ActivityServiceApiBuilder.create()

        service.getFlight().enqueue(object : Callback<PaginateResponse> {
            override fun onResponse(call: Call<PaginateResponse>, response: Response<PaginateResponse>) {
                Log.e("Example", "Esta va segundo")
                if (response.isSuccessful) {
                    val responseFlight = response.body()
                    responseFlight?.let {
                        val flightAdapter = SearchFlightAdapter(it.best_flights.toMutableList())
                        recFlight.setHasFixedSize(true)
                        val layoutManagerFlight = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        recFlight.layoutManager = layoutManagerFlight
                        recFlight.adapter = flightAdapter
                    }
                }
            }

            override fun onFailure(call: Call<PaginateResponse>, t: Throwable) {
                Log.e("Example", t.stackTraceToString())
            }
        })
    }
}