package com.example.fina.data.model

data class CoinStats(
    var total: Int = 0,
    var totalCoins: Int = 0,
    var totalMarkets: Int = 0,
    var totalExchanges: Int = 0,
    var totalMarketCap: String = "",
    var total24hVolume: String = "",
)

data class Coin(
    var uuid: String = "",
    var symbol: String = "",
    var name: String = "",
    var description: String = "",
    var iconUrl: String = "",
    var supply: Supply = Supply(),
    var volume24h: String = "0",
    var marketCap: String = "0",
    var price: String = "0",
    var btcPrice: String = "0",
    var priceAt: Long = 0,
    var change: String = "0",
    var rank: Int = 0,
    var sparkline: List<String> = emptyList(),
    var allTimeHigh: PriceHistory = PriceHistory(),
    var tier: Int = 0,
    var lowVolume: Boolean = false,
    var listedAt: Long = 0,
)

data class Supply(
    var confirmed: Boolean = false,
    var supplyAt: Long = 0,
    var max: String = "0",
    var total: String = "0",
    var circulating: String = "0",
)

data class PriceHistory(
    var price: String = "0",
    var timestamp: Long = 0,
)

object ResponseEntry {
    const val STATUS = "status"
    const val DATA = "data"
    const val COINS = "coins"
    const val COIN = "coin"
    const val PRICE_HISTORY = "history"
    const val STATS = "stats"
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
    FIVE_YEARS("5y");

    override fun toString(): String {
        return displayName
    }
}

enum class OrderBy(private val displayName: String) {
    PRICE("price"),
    MARKET_CAP("marketCap"),
    VOLUME_24H("24hVolume"),
    CHANGE("change"),
    LISTED_AT("listedAt");

    override fun toString(): String {
        return displayName
    }
}

enum class OrderDirection(private val displayName: String) {
    DESC("desc"),
    ASC("asc");

    override fun toString(): String {
        return displayName
    }
}