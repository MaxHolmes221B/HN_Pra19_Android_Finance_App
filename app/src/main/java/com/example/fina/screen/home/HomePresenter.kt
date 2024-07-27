package com.example.fina.screen.home

import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.data.model.OrderBy
import com.example.fina.data.model.OrderDirection
import com.example.fina.data.model.TimePeriod
import com.example.fina.data.repository.CoinRepository
import com.example.fina.data.repository.source.remote.OnResultListener

class HomePresenter internal constructor(private val mCoinRepository: CoinRepository?) : HomeContract.Presenter {
    private var mView: HomeContract.View? = null

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun setView(view: HomeContract.View?) {
        this.mView = view
    }

    override fun getCoins() {
        val referenceCurrencyUuid = "yhjMzLPhuIDl"
        val timePeriod = TimePeriod.TWENTY_FOUR_HOURS
        val uuids = mutableListOf<String>()
        val search = null
        val orderBy = OrderBy.MARKET_CAP
        val orderDirection = OrderDirection.DESC
        val limit = 10
        val offset = 0

        mCoinRepository?.getCoins(
            referenceCurrencyUuid,
            timePeriod,
            uuids,
            search,
            orderBy,
            orderDirection,
            limit,
            offset,
            object : OnResultListener<MutableList<Coin>> {
                override fun onSuccess(data: MutableList<Coin>) {
                    mView?.onGetCoinsSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    mView?.onError(exception)
                }
            }
        )
    }

    override fun getCoinStats() {
        mCoinRepository?.getCoinStats(
            referenceCurrencyUuid = "yhjMzLPhuIDl",
            object : OnResultListener<CoinStats> {
                override fun onSuccess(data: CoinStats) {
                    mView?.onGetCoinStatsSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    mView?.onError(exception)
                }
            }
        )
    }
}