package com.example.fina.data.repository.source.remote

import com.example.fina.data.model.Coin
import com.example.fina.data.repository.source.CoinDataSource
import com.example.fina.data.repository.source.remote.fetchjson.GetJsonFromUrl
import com.example.fina.utils.Constant

class CoinRemoteDataSource : CoinDataSource.Remote {

    override fun getCoins(listener: OnResultListener<MutableList<Coin>>) {
        GetJsonFromUrl(
            urlString = Constant.BASE_URL_COIN,
            keyEntity = "data.coins",
            listener = listener
        )
    }

    companion object {
        private var instance: CoinRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: CoinRemoteDataSource().also { instance = it }
        }
    }
}