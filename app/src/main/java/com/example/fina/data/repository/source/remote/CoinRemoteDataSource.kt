package com.example.fina.data.repository.source.remote

import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.data.model.OrderBy
import com.example.fina.data.model.OrderDirection
import com.example.fina.data.model.PriceHistory
import com.example.fina.data.model.ResponseEntry
import com.example.fina.data.model.TimePeriod
import com.example.fina.data.repository.source.CoinDataSource
import com.example.fina.data.repository.source.remote.fetchjson.GetJsonFromUrl
import com.example.fina.utils.Constant

class CoinRemoteDataSource : CoinDataSource.Remote {

    override fun getCoins(
        referenceCurrencyUuid: String,
        timePeriod: TimePeriod,
        uuids: MutableList<String>,
        search: String?,
        orderBy: OrderBy,
        orderDirection: OrderDirection,
        limit: Int,
        offset: Int,
        listener: OnResultListener<MutableList<Coin>>
    ) {
        val url = StringBuilder(Constant.BASE_URL_COINS)
            .append("referenceCurrencyUuid=$referenceCurrencyUuid")
            .append("&timePeriod=$timePeriod")
            .append("&orderBy=$orderBy")
            .append("&orderDirection=$orderDirection")
            .append("&limit=$limit")
            .append("&offset=$offset")

        search?.let {
            url.append("&search=$search")
        }

        uuids.forEach {
            url.append("&uuids[]=$it")
        }

        GetJsonFromUrl(
            url.toString(),
            ResponseEntry.COINS,
            listener,
        )
    }


    override fun getCoinDetail(
        uuid: String,
        referenceCurrencyUuid: String,
        timePeriod: TimePeriod,
        listener: OnResultListener<Coin>
    ) {
        val url = StringBuilder(Constant.BASE_URL_COIN_DETAIL)
        url.append(uuid)
        url.append("referenceCurrencyUuid=$referenceCurrencyUuid")
        url.append("&timePeriod=$timePeriod")
        GetJsonFromUrl(
            url.toString(),
            ResponseEntry.COIN,
            listener,
        )
    }

    override fun getCoinPriceHistory(
        uuid: String,
        referenceCurrencyUuid: String,
        timePeriod: TimePeriod,
        listener: OnResultListener<MutableList<PriceHistory>>
    ) {
        val url = StringBuilder(Constant.BASE_URL_COIN_DETAIL)
        url.append(uuid)
        url.append("/history?")
        url.append("referenceCurrencyUuid=$referenceCurrencyUuid")
        url.append("&timePeriod=$timePeriod")
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

    companion object {
        private var instance: CoinRemoteDataSource? = null

        fun getInstance() =
            synchronized(this) {
                instance ?: CoinRemoteDataSource().also { instance = it }
            }
    }
}