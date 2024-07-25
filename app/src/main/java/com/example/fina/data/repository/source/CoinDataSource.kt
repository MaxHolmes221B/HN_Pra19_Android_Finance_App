package com.example.fina.data.repository.source

import com.example.fina.data.model.Coin
import com.example.fina.data.repository.source.remote.OnResultListener

interface CoinDataSource {
    interface Local {
        fun getCoinsLocal(listener: OnResultListener<MutableList<Coin>>)
    }

    interface Remote {
        fun getCoins(listener: OnResultListener<MutableList<Coin>>)
    }
}