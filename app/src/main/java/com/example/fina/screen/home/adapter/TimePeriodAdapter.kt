package com.example.fina.screen.home.adapter
//import android.content.Context
//import android.widget.ArrayAdapter
//import android.widget.Spinner
//import com.example.fina.data.model.TimePeriod
//
//class TimePeriodAdapter(context: Context) : ArrayAdapter<TimePeriod>(context, android.R.layout.simple_spinner_item, TimePeriod.values()) {
//    init {
//        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//    }
//
//    override fun getItem(position: Int): TimePeriod? {
//        return TimePeriod.values()[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
//        val view = super.getView(position, convertView, parent)
//        return view
//    }
//
//    override fun getDropDownView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
//        val view = super.getDropDownView(position, convertView, parent)
//        return view
//    }
//}


import android.content.Context
import android.widget.ArrayAdapter
import com.example.fina.data.model.TimePeriod

class TimePeriodAdapter(context: Context, timePeriods: Array<TimePeriod>) : ArrayAdapter<TimePeriod>(context, android.R.layout.simple_spinner_item, timePeriods) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}
