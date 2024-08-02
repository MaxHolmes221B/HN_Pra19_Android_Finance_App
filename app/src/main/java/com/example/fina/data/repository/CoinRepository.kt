package com.example.fina.data.repository

import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.data.model.Currency
import com.example.fina.data.model.PriceRecord
import com.example.fina.data.repository.source.CoinDataSource
import com.example.fina.utils.CurrencyParam
import com.example.fina.utils.ExtraParams
import com.example.fina.utils.OrderProperties

class CoinRepository private constructor(
    private val remote: CoinDataSource.Remote,
    private val local: CoinDataSource.Local,
) : CoinDataSource.Remote, CoinDataSource.Local {
    override fun getLocalCoins(listener: OnResultListener<List<Coin>>) {
        TODO("Not yet implemented")
    }

    override fun getCoins(
        params: ExtraParams,
        orderProperties: OrderProperties,
        listener: OnResultListener<List<Coin>>,
    ) {
        remote.getCoins(
            params,
            orderProperties,
            listener,
        )
    }

    override fun getCoinStats(
        referenceCurrencyUuid: String,
        listener: OnResultListener<CoinStats>
    ) {
        remote.getCoinStats(referenceCurrencyUuid, listener)
    }

    override fun getCoinDetail(
        uuid: String,
        params: ExtraParams,
        listener: OnResultListener<Coin>,
    ) {
        remote.getCoinDetail(uuid, params, listener)
    }

    override fun getPriceRecord(
        uuid: String,
        params: ExtraParams,
        listener: OnResultListener<List<PriceRecord>>,
    ) {
        remote.getPriceRecord(uuid, params, listener)
    }

    override fun getCurrencies(params: CurrencyParam, listener: OnResultListener<List<Currency>>) {
        remote.getCurrencies(params, listener)
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