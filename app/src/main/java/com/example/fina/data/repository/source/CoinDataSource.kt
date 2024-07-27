package com.example.fina.data.repository.source

import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.data.model.OrderBy
import com.example.fina.data.model.OrderDirection
import com.example.fina.data.model.PriceHistory
import com.example.fina.data.model.TimePeriod
import com.example.fina.data.repository.source.remote.OnResultListener
import com.example.fina.utils.Constant

interface CoinDataSource {
    interface Local {
        fun getLocalCoins(listener: OnResultListener<MutableList<Coin>>)
    }

    interface Remote {
        fun getCoins(
            referenceCurrencyUuid: String = Constant.USD_UUID,
            timePeriod: TimePeriod = TimePeriod.TWENTY_FOUR_HOURS,
            uuids: MutableList<String> = mutableListOf(),
            search: String? = null,
            orderBy: OrderBy = OrderBy.MARKET_CAP,
            orderDirection: OrderDirection = OrderDirection.DESC,
            limit: Int = Constant.DEFAULT_LIMIT,
            offset: Int = 0,
            listener: OnResultListener<MutableList<Coin>>
        )

        fun getCoinDetail(
            uuid: String,
            referenceCurrencyUuid: String = Constant.USD_UUID,
            timePeriod: TimePeriod = TimePeriod.TWENTY_FOUR_HOURS,
            listener: OnResultListener<Coin>,
        )

        fun getCoinPriceHistory(
            uuid: String,
            referenceCurrencyUuid: String = Constant.USD_UUID,
            timePeriod: TimePeriod = TimePeriod.TWENTY_FOUR_HOURS,
            listener: OnResultListener<MutableList<PriceHistory>>
        )

        fun getCoinStats(
            referenceCurrencyUuid: String = Constant.USD_UUID,
            listener: OnResultListener<CoinStats>
        )

    }
}