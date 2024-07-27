package com.example.fina.data.repository

import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.data.model.OrderBy
import com.example.fina.data.model.OrderDirection
import com.example.fina.data.model.PriceHistory
import com.example.fina.data.model.TimePeriod
import com.example.fina.data.repository.source.CoinDataSource
import com.example.fina.data.repository.source.remote.OnResultListener

class CoinRepository private constructor(
    private val remote: CoinDataSource.Remote,
    private val local: CoinDataSource.Local,
) : CoinDataSource.Remote, CoinDataSource.Local {
    override fun getLocalCoins(listener: OnResultListener<MutableList<Coin>>) {
        TODO("Not yet implemented")
    }

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
        remote.getCoins(
            referenceCurrencyUuid,
            timePeriod,
            uuids,
            search,
            orderBy,
            orderDirection,
            limit,
            offset,
            listener
        );
    }

    override fun getCoinStats(
        referenceCurrencyUuid: String,
        listener: OnResultListener<CoinStats>
    ) {
        remote.getCoinStats(referenceCurrencyUuid, listener)
    }

    override fun getCoinDetail(
        uuid: String,
        referenceCurrencyUuid: String,
        timePeriod: TimePeriod,
        listener: OnResultListener<Coin>,
    ) {
        remote.getCoinDetail(uuid, referenceCurrencyUuid, timePeriod, listener)
    }

    override fun getCoinPriceHistory(
        uuid: String,
        referenceCurrencyUuid: String,
        timePeriod: TimePeriod,
        listener: OnResultListener<MutableList<PriceHistory>>
    ) {
        remote.getCoinPriceHistory(uuid, referenceCurrencyUuid, timePeriod, listener)
    }

    companion object {
        private var instance: CoinRepository? = null

        fun getInstance(
            remote: CoinDataSource.Remote,
            local: CoinDataSource.Local,
        ) = synchronized(this) {
            instance ?: CoinRepository(remote, local).also { instance = it }
        }
    }
}