package com.edu.ort.tp3_belgrano_a_grupo4.adapters.com.edu.ort.tp3_belgrano_a_grupo4.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.edu.ort.tp3_belgrano_a_grupo4.R

class CustomSpinnerAdapter(
    private val context: Context,
    private val items: List<String>,
    private val icons: List<Int>
) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(R.layout.spinner_item, parent, false)

        val icon: ImageView = view.findViewById(R.id.icon)
        val text: TextView = view.findViewById(R.id.text)

        // Set the icon and text for each item
        icon.setImageResource(icons[position])
        text.text = items[position]

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getView(position, convertView, parent)
    }
}
