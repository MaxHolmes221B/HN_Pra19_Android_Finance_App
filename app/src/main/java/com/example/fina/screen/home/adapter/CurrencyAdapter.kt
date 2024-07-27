package com.example.fina.screen.home.adapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner

class CurrencyAdapter(context: Context, currencies: Array<String>) : ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, currencies) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}
