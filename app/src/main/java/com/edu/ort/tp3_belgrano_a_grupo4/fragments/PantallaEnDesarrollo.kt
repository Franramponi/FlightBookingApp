package com.edu.ort.tp3_belgrano_a_grupo4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.edu.ort.tp3_belgrano_a_grupo4.R


class PantallaEnDesarrollo : Fragment() {
    private lateinit var viewDesarrollo: View
    private lateinit var backButton: ImageView

    companion object {
        val BUTTON_BACK = R.id.btn_back_screen_dev
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDesarrollo = inflater.inflate(R.layout.fragment_pantalla_en_desarrollo, container, false)

        initViews()


        return viewDesarrollo
    }
    override fun onStart() {
        super.onStart()
        initListeners()

    }
    private fun initViews() {
        backButton = viewDesarrollo.findViewById(BUTTON_BACK)
    }

    private fun initListeners() {
        backButton.setOnClickListener { navigateToHome() }
    }

    private fun navigateToHome() {
        val action1 = PantallaEnDesarrolloDirections.actionPantallaEnDesarrolloToExplore()
        viewDesarrollo.findNavController().navigate(action1)
    }

}