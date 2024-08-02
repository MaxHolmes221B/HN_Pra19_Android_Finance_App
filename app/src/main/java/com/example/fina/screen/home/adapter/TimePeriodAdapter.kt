package com.example.fina.screen.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fina.R
import com.example.fina.utils.TimePeriod
import com.example.fina.utils.TimePeriodOption

class TimePeriodAdapter(context: Context, timePeriodOptions: ArrayList<TimePeriodOption>) :
    ArrayAdapter<TimePeriodOption>(context, android.R.layout.simple_spinner_item, timePeriodOptions) {
    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.custom_spinner, parent, false
        )

        val item = getItem(position)
        val textView = view.findViewById<TextView>(R.id.textviewSpinner)
        textView.text = item?.getName(context)

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.custom_spinner, parent, false
        )

        val item = getItem(position)
        val textView = view.findViewById<TextView>(R.id.textviewSpinner)
        textView.text = item?.getName(context)

        return view
    }
}