package com.edu.ort.tp3_belgrano_a_grupo4.fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.edu.ort.tp3_belgrano_a_grupo4.R
import com.edu.ort.tp3_belgrano_a_grupo4.adapters.com.edu.ort.tp3_belgrano_a_grupo4.adapters.CustomSpinnerAdapter


class Search : Fragment() {
    private lateinit var viewSearch: View
    private lateinit var btnOneWay: Button
    private lateinit var btnRoundTrip: Button
    private lateinit var editTextDepartureDate: EditText
    private lateinit var editTextArrivalDate: EditText
    private lateinit var spinnerPasajeros: Spinner
    private lateinit var anotherSpinner: Spinner
    private lateinit var btnSearch: Button

    companion object {
        val BTN_ONE_DAY_ID = R.id.btnOneWay
        val BTN_ROUND_TRIP_ID = R.id.btnRoundTrip
        val INPUT_DEPARTURE_DATE_ID = R.id.et_departure_date
        val INPUT_ARRIVAL_DATE_ID = R.id.et_arrival_date
        val BTN_SEARCH_ID = R.id.btnSearch
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewSearch =  inflater.inflate(R.layout.fragment_search, container, false)


        initViews()
        initListeners()
        initSpinners()
        btnOneWay.isSelected = true
        updateButtonAppearance()

        return viewSearch
    }

    private fun initViews() {
        btnOneWay = viewSearch.findViewById(BTN_ONE_DAY_ID)
        btnRoundTrip = viewSearch.findViewById(BTN_ROUND_TRIP_ID)
        editTextDepartureDate =viewSearch.findViewById(INPUT_DEPARTURE_DATE_ID)
        editTextArrivalDate =viewSearch.findViewById(INPUT_ARRIVAL_DATE_ID)
        spinnerPasajeros = viewSearch.findViewById(R.id.pasajeros)
        anotherSpinner = viewSearch.findViewById(R.id.clases)
        btnSearch = viewSearch.findViewById(BTN_SEARCH_ID)
    }

    private fun initListeners() {
        btnOneWay.setOnClickListener {
            btnOneWay.isSelected = true
            btnRoundTrip.isSelected = false
            updateButtonAppearance()
            editTextArrivalDate.visibility = View.GONE
        }

        btnRoundTrip.setOnClickListener{
            btnOneWay.isSelected = false
            btnRoundTrip.isSelected = true
            updateButtonAppearance()
            editTextArrivalDate.visibility = View.VISIBLE
        }
        editTextDepartureDate.setOnClickListener { showDatePicker(editTextDepartureDate) }
        editTextArrivalDate.setOnClickListener { showDatePicker(editTextArrivalDate) }

        btnSearch.setOnClickListener { navigateToResults() }
    }

    private fun updateButtonAppearance() {
        // Método para actualizar la apariencia de los botones según su estado de selección
        if (btnOneWay.isSelected) {
            btnOneWay.setBackgroundResource(R.drawable.selected_button_background)
            btnOneWay.setTextColor(resources.getColor(android.R.color.white))
            btnRoundTrip.setBackgroundResource(R.drawable.unselected_button_background)
            btnRoundTrip.setTextColor(resources.getColor(android.R.color.darker_gray))
        } else {
            btnRoundTrip.setBackgroundResource(R.drawable.selected_button_background)
            btnRoundTrip.setTextColor(resources.getColor(android.R.color.white))
            btnOneWay.setBackgroundResource(R.drawable.unselected_button_background)
            btnOneWay.setTextColor(resources.getColor(android.R.color.darker_gray))
        }
    }

    private fun showDatePicker(editText: EditText) {
        // Obtener la fecha actual
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        // Crear un DatePickerDialog y mostrarlo
        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            // Actualizar el texto del EditText con la fecha seleccionada
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            editText.setText(selectedDate)
        }, year, month, dayOfMonth)

        // Mostrar el DatePickerDialog
        datePickerDialog.show()
    }


    private fun initSpinners() {
        // Configurar el adaptador del spinner de pasajeros
        val pasajerosArray = resources.getStringArray(R.array.spinner_pasajeros).toList()
        val pasajeroIcono = R.drawable.ic_pasajeros
        val listaIconoPasajero = List(pasajerosArray.size) { pasajeroIcono }
        val pasajeroAdapter = CustomSpinnerAdapter(requireContext(), pasajerosArray, listaIconoPasajero)
        spinnerPasajeros.adapter = pasajeroAdapter

        // Configurar el adaptador del otro spinner
        val clasesArray = resources.getStringArray(R.array.spinner_clase).toList()
        val claseIcono = R.drawable.ic_clases
        val listaIconoClase = List(clasesArray.size) { claseIcono }
        val claseAdapter = CustomSpinnerAdapter(requireContext(), clasesArray, listaIconoClase)
        anotherSpinner.adapter = claseAdapter
    }


    private fun navigateToResults() {
        val action = SearchDirections.actionSearchToSearchResult()
        findNavController().navigate(action)
    }

}