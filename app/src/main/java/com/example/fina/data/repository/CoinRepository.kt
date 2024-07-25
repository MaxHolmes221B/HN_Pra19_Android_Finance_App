package com.example.fina.data.repository

import com.example.fina.data.model.Coin
import com.example.fina.data.repository.source.CoinDataSource
import com.example.fina.data.repository.source.remote.CoinRemoteDataSource
import com.example.fina.data.repository.source.remote.OnResultListener

class CoinRepository private constructor(
    private val remote: CoinDataSource.Remote,
    private val local: CoinDataSource.Local
) : CoinDataSource.Local, CoinDataSource.Remote {

    override fun getCoinsLocal(listener: OnResultListener<MutableList<Coin>>) {
        // Implement local data source if needed
        TODO("Not yet implemented")
    }

    override fun getCoins(listener: OnResultListener<MutableList<Coin>>) {
        remote.getCoins(listener)
    }

    companion object {
        private var instance: CoinRepository? = null

        fun getInstance(remote: CoinDataSource.Remote, local: CoinDataSource.Local) = synchronized(this) {
            instance ?: CoinRepository(remote, local).also { instance = it }
        }
    }
}
