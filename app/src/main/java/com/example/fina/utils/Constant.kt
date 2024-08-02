package com.example.fina.utils

import android.content.Context

object Constant {
    const val BASE_URL = "https://api.coinranking.com/v2"
    const val BASE_URL_COINS = "https://api.coinranking.com/v2/coins"
    const val BASE_URL_CURRENCIES = "https://api.coinranking.com/v2/reference-currencies"
    const val BASE_URL_COIN_DETAIL = "https://api.coinranking.com/v2/coin/"
    const val USD_UUID = "yhjMzLPhuIDl"
    const val DEFAULT_LIMIT = 15
    const val BASE_API_KEY = "coinranking8af1735d570619af6e8a4bff7925909438489a36d5886a5a"
}

enum class TimePeriod(private val displayName: String) {
    ONE_HOUR("1h"),
    THREE_HOURS("3h"),
    TWELVE_HOURS("12h"),
    TWENTY_FOUR_HOURS("24h"),
    SEVEN_DAYS("7d"),
    THIRTY_DAYS("30d"),
    THREE_MONTHS("3m"),
    ONE_YEAR("1y"),
    THREE_YEARS("3y"),
    FIVE_YEARS("5y"),
    ;

    override fun toString(): String {
        return displayName
    }
}




class OrderBy(val nameResId: Int, val value: String) {
    fun getName(context: Context): String {
        return context.getString(nameResId)
    }
}

class TimePeriodOption(val nameResId: Int, val value: String) {
    fun getName(context: Context): String {
        return context.getString(nameResId)
    }
}


enum class OrderDirection(private val displayName: String) {
    DESC("desc"),
    ASC("asc"),
    ;

    override fun toString(): String {
        return displayName
    }
}