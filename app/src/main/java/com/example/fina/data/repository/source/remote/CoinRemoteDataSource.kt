package com.example.fina.data.repository.source.remote

import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.data.model.Currency
import com.example.fina.data.model.PriceRecord
import com.example.fina.utils.ResponseEntry
import com.example.fina.data.repository.OnResultListener
import com.example.fina.data.repository.source.CoinDataSource
import com.example.fina.data.repository.source.remote.fetchjson.GetJsonFromUrl
import com.example.fina.utils.Constant
import com.example.fina.utils.CurrencyParam
import com.example.fina.utils.ExtraParams
import com.example.fina.utils.OrderProperties

class CoinRemoteDataSource : CoinDataSource.Remote {

    override fun getCoins(
        params: ExtraParams,
        orderProperties: OrderProperties,
        listener: OnResultListener<List<Coin>>,
    ) {
        val url: StringBuilder = StringBuilder(Constant.BASE_URL_COINS)
        url.append("$params")
        url.append("&$orderProperties")
        GetJsonFromUrl(
            url.toString(),
            ResponseEntry.COINS,
            listener,
        )
    }

    override fun getCoinDetail(
        uuid: String,
        params: ExtraParams,
        listener: OnResultListener<Coin>,
    ) {
        val url = StringBuilder(Constant.BASE_URL_COIN_DETAIL)
        url.append(uuid)
        url.append("?$params")
        GetJsonFromUrl(
            url.toString(),
            ResponseEntry.COIN,
            listener,
        )
    }

    override fun getPriceRecord(
        uuid: String,
        params: ExtraParams,
        listener: OnResultListener<List<PriceRecord>>,
    ) {
        val url = StringBuilder(Constant.BASE_URL_COIN_DETAIL)
        url.append(uuid)
        url.append("/history?$params")
        GetJsonFromUrl(
            url.toString(),
            ResponseEntry.PRICE_HISTORY,
            listener,
        )
    }

    override fun getCoinStats(
        referenceCurrencyUuid: String,
        listener: OnResultListener<CoinStats>
    ) {
        val url = StringBuilder(Constant.BASE_URL_COINS)
            .append("referenceCurrencyUuid=$referenceCurrencyUuid")

        GetJsonFromUrl(
            url.toString(),
            ResponseEntry.STATS,
            listener,
        )
    }

    override fun getCurrencies(
        params: CurrencyParam,
        listener: OnResultListener<List<Currency>>) {
        var url = StringBuilder(Constant.BASE_URL_CURRENCIES)
        url.append("$params")
        GetJsonFromUrl(
            url.toString(),
            ResponseEntry.CURRENCIES,
            listener,
        )
    }

    companion object {
        private var instance: CoinRemoteDataSource? = null

        fun getInstance() =
            synchronized(this) {
                instance ?: CoinRemoteDataSource().also { instance = it }
            }
    }
}