package com.example.fina.data.repository.source

import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.data.model.Currency
import com.example.fina.data.model.PriceRecord
import com.example.fina.data.repository.OnResultListener
import com.example.fina.utils.Constant
import com.example.fina.utils.CurrencyParam
import com.example.fina.utils.ExtraParams
import com.example.fina.utils.OrderProperties

interface CoinDataSource {
    interface Local {
        fun getLocalCoins(listener: OnResultListener<List<Coin>>)
    }

    interface Remote {
        fun getCoins(
        params: ExtraParams = ExtraParams(),
        orderProperties: OrderProperties = OrderProperties(),
        listener: OnResultListener<List<Coin>>,
        )

        fun getCoinStats(
            referenceCurrencyUuid: String = Constant.USD_UUID,
            listener: OnResultListener<CoinStats>
        )

        fun getCoinDetail(
            uuid: String,
            params: ExtraParams,
            listener: OnResultListener<Coin>,
        )

        fun getPriceRecord(
            uuid: String,
            params: ExtraParams,
            listener: OnResultListener<List<PriceRecord>>,
        )
        fun getCurrencies(
            params: CurrencyParam,
            listener: OnResultListener<List<Currency>>
        )
    }
}