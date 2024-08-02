package com.example.fina.screen.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fina.R
import com.example.fina.data.model.Currency

class CurrencyAdapter(context: Context, private val currencies: ArrayList<Currency>) : ArrayAdapter<Currency>(context, R.layout.custom_spinner, currencies) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false)
        val item = getItem(position)
        val textView = view.findViewById<TextView>(R.id.textviewSpinner)
        textView.text = item?.symbol
        return view
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false)
        val item = getItem(position)
        val textView = view.findViewById<TextView>(R.id.textviewSpinner)
        textView.text = item?.symbol
        return view
    }

    fun updateData(newCurrencies: ArrayList<Currency>) {
        currencies.clear()
        currencies.addAll(newCurrencies)
        notifyDataSetChanged()
    }
}
