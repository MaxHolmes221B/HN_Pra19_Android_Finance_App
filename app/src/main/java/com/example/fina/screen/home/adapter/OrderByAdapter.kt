package com.example.fina.screen.home.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.example.fina.data.model.OrderBy

class OrderByAdapter(context: Context, orderByOptions: Array<OrderBy>) : ArrayAdapter<OrderBy>(context, android.R.layout.simple_spinner_item, orderByOptions) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}
