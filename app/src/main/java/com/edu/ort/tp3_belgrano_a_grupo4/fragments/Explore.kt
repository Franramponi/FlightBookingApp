package com.edu.ort.tp3_belgrano_a_grupo4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edu.ort.tp3_belgrano_a_grupo4.R
import com.edu.ort.tp3_belgrano_a_grupo4.adapters.OfferAdapter
import com.edu.ort.tp3_belgrano_a_grupo4.adapters.TrendingAdapter
import com.edu.ort.tp3_belgrano_a_grupo4.adapters.WeeklyFlightAdapter
import com.edu.ort.tp3_belgrano_a_grupo4.entities.Offer
import com.edu.ort.tp3_belgrano_a_grupo4.entities.TrendingDestination
import com.edu.ort.tp3_belgrano_a_grupo4.entities.WeeklyFlight

class Explore : Fragment() {

    private lateinit var view1: View
    private lateinit var recWeekly: RecyclerView
    private lateinit var recTrending: RecyclerView
    private lateinit var recOffer: RecyclerView
    private lateinit var exploreButton: Button

    private var offers: MutableList<Offer> = ArrayList()
    private var trendings: MutableList<TrendingDestination> = ArrayList()
    private var weeklyflights: MutableList<WeeklyFlight> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_explore, container, false)
        recWeekly = view1.findViewById(R.id.rec_weekly_flifht_list)
        recTrending = view1.findViewById(R.id.rec_tranding_dest_list)
        recOffer = view1.findViewById(R.id.rec_offers_list_explore)
        exploreButton = view1.findViewById(R.id.rec_btn_flight_explore)


        exploreButton.setOnClickListener {
            findNavController().navigate(R.id.fragment_search)
        }
        return view1
    }

    override fun onStart() {
        super.onStart()


        recWeekly.setHasFixedSize(true)
        recTrending.setHasFixedSize(true)
        recOffer.setHasFixedSize(true)

        val layoutManagerWeekly = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerTrending = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerOffer = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val offerAdapter = OfferAdapter(offers){ offer ->
            val action = ExploreDirections.actionExploreToOffers()
            findNavController().navigate(action)
        }

        val trendingAdapter = TrendingAdapter(trendings){ _ ->
            val action = ExploreDirections.actionExploreToBoracay()
            findNavController().navigate(action)
        }
        val weeklyFlightAdapter = WeeklyFlightAdapter(weeklyflights)

        recWeekly.layoutManager = layoutManagerWeekly
        recTrending.layoutManager = layoutManagerTrending
        recOffer.layoutManager = layoutManagerOffer

        recWeekly.adapter = weeklyFlightAdapter
        recTrending.adapter = trendingAdapter
        recOffer.adapter = offerAdapter
    }


    private fun loadList() {
        offers.add(Offer("Mastercard", R.drawable.card_mastercard, 20))
        offers.add(Offer("Visa", R.drawable.card_visa, 25))

        trendings.add(TrendingDestination("Philippines", "Boracay", "5D4N", R.drawable.img_trending))
        trendings.add(TrendingDestination("Maldives", "Baros", "7D6N", R.drawable.img_trending2))
        trendings.add(TrendingDestination("Indonesia", "Bali", "3D2N", R.drawable.img_trending3))
        trendings.add(TrendingDestination("Philippines", "Palawan", "3D2N", R.drawable.img_trending4))

        weeklyflights.add(WeeklyFlight("Paris", 1299))
    }
}
