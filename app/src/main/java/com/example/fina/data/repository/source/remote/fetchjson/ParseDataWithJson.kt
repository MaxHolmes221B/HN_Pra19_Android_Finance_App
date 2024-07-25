package com.example.fina.data.repository.source.remote.fetchjson

import android.util.Log
import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ParseDataWithJson {
    fun parseJsonToData(jsonObject: JSONObject?, keyEntity: String): Any {
        return when (keyEntity) {
            "data.coins" -> parseCoins(jsonObject?.optJSONObject("data")?.optJSONArray("coins"))
            "data.stats" -> parseCoinStats(jsonObject?.optJSONObject("data")?.optJSONObject("stats"))
            else -> emptyList<Any>() // Default case or unknown keyEntity
        }
    }

    private fun parseCoins(jsonArray: JSONArray?): List<Coin> {
        val coins = mutableListOf<Coin>()
        try {
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val coinObject = jsonArray?.optJSONObject(i)
                val coin = parseCoin(coinObject)
                coin?.let {
                    coins.add(it)
                }
            }
        } catch (e: JSONException) {
            Log.e("ParseDataWithJson", "parseCoins: ", e)
        }
        return coins
    }

    private fun parseCoin(jsonObject: JSONObject?): Coin? {
        try {
            jsonObject?.let {
                return Coin(
                    uuid = it.optString("uuid"),
                    symbol = it.optString("symbol"),
                    name = it.optString("name"),
                    color = it.optString("color"),
                    iconUrl = it.optString("iconUrl"),
                    marketCap = it.optString("marketCap"),
                    price = it.optString("price"),
                    listedAt = it.optLong("listedAt"),
                    tier = it.optInt("tier"),
                    change = it.optString("change"),
                    rank = it.optInt("rank"),
                    sparkline = parseSparkline(it.optJSONArray("sparkline")),
                    lowVolume = it.optBoolean("lowVolume"),
                    coinrankingUrl = it.optString("coinrankingUrl"),
                    volume = it.optString("24hVolume"),
                    btcPrice = it.optString("btcPrice"),
                    contractAddresses = parseContractAddresses(it.optJSONArray("contractAddresses"))
                )
            }
        } catch (e: JSONException) {
            Log.e("ParseDataWithJson", "parseCoin: ", e)
        }
        return null
    }

    private fun parseSparkline(jsonArray: JSONArray?): List<String?> {
        val sparkline = mutableListOf<String?>()
        jsonArray?.let {
            for (i in 0 until it.length()) {
                sparkline.add(it.optString(i))
            }
        }
        return sparkline
    }

    private fun parseContractAddresses(jsonArray: JSONArray?): List<String> {
        val contractAddresses = mutableListOf<String>()
        jsonArray?.let {
            for (i in 0 until it.length()) {
                contractAddresses.add(it.optString(i))
            }
        }
        return contractAddresses
    }

    private fun parseCoinStats(jsonObject: JSONObject?): CoinStats {
        return CoinStats(
            total = jsonObject?.optInt("total") ?: 0,
            totalCoins = jsonObject?.optInt("totalCoins") ?: 0,
            totalMarkets = jsonObject?.optInt("totalMarkets") ?: 0,
            totalExchanges = jsonObject?.optInt("totalExchanges") ?: 0,
            totalMarketCap = jsonObject?.optString("totalMarketCap") ?: "",
            total24hVolume = jsonObject?.optString("total24hVolume") ?: ""
        )
    }
}