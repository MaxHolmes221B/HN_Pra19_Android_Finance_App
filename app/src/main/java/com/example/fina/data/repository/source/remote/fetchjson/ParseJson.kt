package com.example.fina.data.repository.source.remote.fetchjson


import android.util.Log
import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import org.json.JSONArray
import org.json.JSONObject

class ParseJson {

    fun coinParseJson(jsonObject: JSONObject): Coin {
        return Coin().apply {
            uuid = jsonObject.getString("uuid")
            symbol = jsonObject.getString("symbol")
            name = jsonObject.getString("name")
            color = jsonObject.optString("color")
            iconUrl = jsonObject.optString("iconUrl")
            marketCap = jsonObject.getString("marketCap")
            price = jsonObject.getString("price")
            listedAt = jsonObject.getLong("listedAt")
            tier = jsonObject.getInt("tier")
            change = jsonObject.getString("change")
            rank = jsonObject.getInt("rank")
            sparkline = parseSparkline(jsonObject.optJSONArray("sparkline"))
            lowVolume = jsonObject.optBoolean("lowVolume")
            coinrankingUrl = jsonObject.getString("coinrankingUrl")
            volume = jsonObject.optString("24hVolume")
            btcPrice = jsonObject.getString("btcPrice")
            contractAddresses = parseContractAddresses(jsonObject.optJSONArray("contractAddresses"))
        }
    }

    fun coinStatsParseJson(jsonObject: JSONObject): CoinStats {
        return CoinStats().apply {
            total = jsonObject.getInt("total")
            totalCoins = jsonObject.getInt("totalCoins")
            totalMarkets = jsonObject.getInt("totalMarkets")
            totalExchanges = jsonObject.getInt("totalExchanges")
            totalMarketCap = jsonObject.getString("totalMarketCap")
            total24hVolume = jsonObject.getString("total24hVolume")
        }
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
}
