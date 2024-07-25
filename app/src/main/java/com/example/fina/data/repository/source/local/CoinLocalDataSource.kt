package com.example.fina.data.repository.source.local

import com.example.fina.data.model.Coin
import com.example.fina.data.repository.source.CoinDataSource
import com.example.fina.data.repository.source.remote.OnResultListener

class CoinLocalDataSource : CoinDataSource.Local {

    override fun getCoinsLocal(listener: OnResultListener<MutableList<Coin>>) {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: CoinLocalDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: CoinLocalDataSource().also { instance = it }
        }
    }
}