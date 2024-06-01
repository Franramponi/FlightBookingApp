package com.edu.ort.tp3_belgrano_a_grupo4.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.edu.ort.tp3_belgrano_a_grupo4.R

class Profile : Fragment() {
    private lateinit var viewProfile: View
    private lateinit var backButton: ImageView
    private lateinit var payButton: LinearLayout
    private lateinit var referralButton: LinearLayout
    private lateinit var settingButton: LinearLayout
    private lateinit var logoutButton: LinearLayout
    companion object {
        val BUTTON_BACK = R.id.btn_back_fragment_perfil
        val BUTTON_PAY = R.id.layout_payment
        val BUTTON_REFERRA = R.id.layout_referral
        val BUTTON_SETTING = R.id.layout_setting
        val BUTTON_LOGOUT = R.id.layout_logout
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewProfile =  inflater.inflate(R.layout.fragment_profile, container, false)
        requireActivity().actionBar?.hide()

        initViews()


        return viewProfile
    }
    override fun onStart() {
        super.onStart()
        initListeners()

    }

    private fun initViews() {
        backButton = viewProfile.findViewById(BUTTON_BACK)
        payButton = viewProfile.findViewById(BUTTON_PAY)
        referralButton = viewProfile.findViewById(BUTTON_REFERRA)
        settingButton= viewProfile.findViewById(BUTTON_SETTING)
        logoutButton = viewProfile.findViewById(BUTTON_LOGOUT)
    }

    private fun initListeners() {
        backButton.setOnClickListener { navigateToHome() }
        payButton.setOnClickListener { navigateToScreenDeve() }
        referralButton.setOnClickListener { navigateToScreenDeve() }
        settingButton.setOnClickListener { navigateToHome() }
        logoutButton.setOnClickListener { navigateToLogout() }
    }
    private fun alertCerrarSesion() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.txt_cerrar_sesion)
        builder.setMessage(R.string.txt_pregunta_cerrar_sesion)
        builder.setPositiveButton(R.string.txt_cerrar_sesion) { dialog, _ ->
            // Cerrar sesión
            Toast.makeText(requireContext(), R.string.txt_sesion_cerrada, Toast.LENGTH_SHORT).show()
            val activity = requireActivity() //->Activity asociada al fragment
            activity.finish()
            dialog.dismiss()
        }
        builder.setNegativeButton(R.string.cancelar) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
    private fun navigateToScreenDeve() {
        val action = ProfileDirections.actionProfileToPantallaEnDesarrollo()
        viewProfile.findNavController().navigate(action)

    }

    private fun navigateToLogout() {
        alertCerrarSesion()
    }

    private fun navigateToHome() {
        val action = ProfileDirections.actionProfileToExplore()
        viewProfile.findNavController().navigate(action)
    }
}

