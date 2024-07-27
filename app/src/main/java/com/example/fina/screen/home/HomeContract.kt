package com.example.fina.screen.home

import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.utils.base.BasePresenter
import java.lang.Exception

class HomeContract {
    interface View {
        fun onGetCoinsSuccess(coins: MutableList<Coin>)
        fun onGetCoinStatsSuccess(coinStats: CoinStats)
        fun onError(exception: Exception?)
    }
    interface Presenter : BasePresenter<View> {
        fun getCoins()
        fun getCoinStats()
    }
}