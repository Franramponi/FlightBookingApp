package com.edu.ort.tp3_belgrano_a_grupo4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edu.ort.tp3_belgrano_a_grupo4.R

class search_result : Fragment() {
    private lateinit var viewSearchResult: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewSearchResult = inflater.inflate(R.layout.fragment_search_result, container, false)

        return viewSearchResult
    }

    companion object {

    }

}