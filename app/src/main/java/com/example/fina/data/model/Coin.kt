package com.example.fina.data.model

import java.io.Serializable

data class Coin(
    var uuid: String = "",
    var symbol: String = "",
    var name: String = "",
    var color: String? = "",
    var iconUrl: String? = "",
    var marketCap: String = "",
    var price: String = "",
    var listedAt: Long = 0,
    var tier: Int = 0,
    var change: String = "",
    var rank: Int = 0,
    var sparkline: List<String?> = emptyList(),
    var lowVolume: Boolean = false,
    var coinrankingUrl: String = "",
    var volume: String = "",
    var btcPrice: String = "",
    var contractAddresses: List<String> = emptyList()
): Serializable


data class CoinStats(
    var total: Int = 0,
    var totalCoins: Int = 0,
    var totalMarkets: Int = 0,
    var totalExchanges: Int = 0,
    var totalMarketCap: String = "",
    var total24hVolume: String = ""
): Serializable