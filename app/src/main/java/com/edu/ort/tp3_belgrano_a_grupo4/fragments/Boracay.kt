package com.edu.ort.tp3_belgrano_a_grupo4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.edu.ort.tp3_belgrano_a_grupo4.R


class Boracay : Fragment() {

    private lateinit var viewBoracai: View
    private lateinit var buttonBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBoracai =  inflater.inflate(R.layout.fragment_boracay, container, false)
        buttonBack  = viewBoracai.findViewById(R.id.imageView11)
        buttonBack.setOnClickListener {
            val action = BoracayDirections.actionBoracayToExplore()
            viewBoracai.findNavController().navigate(action)
        }

        return viewBoracai;
    }



}