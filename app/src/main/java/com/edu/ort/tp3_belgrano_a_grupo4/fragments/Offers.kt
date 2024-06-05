package com.edu.ort.tp3_belgrano_a_grupo4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.edu.ort.tp3_belgrano_a_grupo4.R
import com.edu.ort.tp3_belgrano_a_grupo4.database.AppDatabase
import com.edu.ort.tp3_belgrano_a_grupo4.database.dao.FavoriteDao
import com.edu.ort.tp3_belgrano_a_grupo4.database.entities.Favorite
import kotlinx.coroutines.launch

class Offers : Fragment() {

    private lateinit var viewOffers: View
    private lateinit var database: AppDatabase
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var corazonOne: ImageView
    private lateinit var corazonTwo: ImageView

    companion object {
        val BTN_CORAZON_1 = R.id.corazon_1
        val BTN_CORAZON_2 = R.id.corazon_2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewOffers = inflater.inflate(R.layout.fragment_offers, container, false)

        database = AppDatabase.getInstance(requireContext())
        favoriteDao = database.favoriteDao()

        initViews()
        initListeners()

        // Carga el estado inicial de los corazones
        lifecycleScope.launch {
            updateHeartImage(corazonOne, 1)
            updateHeartImage(corazonTwo, 2)
        }

        return viewOffers
    }

    private fun initViews() {
        corazonOne = viewOffers.findViewById(BTN_CORAZON_1)
        corazonTwo = viewOffers.findViewById(BTN_CORAZON_2)
    }

    private fun initListeners() {
        heartClick(corazonOne, 1)
        heartClick(corazonTwo, 2)
    }

    private fun heartClick(heart: ImageView, id: Int) {
        heart.setOnClickListener {
            lifecycleScope.launch {
                val favorite = favoriteDao.getFavoriteById(id)
                val isFavorite = favorite?.isFavorite != true

                val anim: Animation = AnimationUtils.loadAnimation(context, R.anim.anim_corazon)
                heart.startAnimation(anim)

                favoriteDao.insert(Favorite(id, isFavorite))
                updateHeartImage(heart, id)
            }
        }
    }

    private suspend fun updateHeartImage(heart: ImageView, id: Int) {
        val favorite = favoriteDao.getFavoriteById(id)
        val isFavorite = favorite?.isFavorite == true
        heart.setImageResource(
            if (isFavorite){ R.drawable.corazon_lleno} else{ R.drawable.corazon_vacio}
        )
    }
}