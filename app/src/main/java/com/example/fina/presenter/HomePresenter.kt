package com.example.fina.presenter

import com.example.fina.data.repository.CoinRepository
import com.example.fina.screen.MainActivity

class HomePresenter(private val view: MainActivity) {

    private val repository = CoinRepository()

    fun loadCoins() {
        val coinsResponse = repository.fetchCoins()
        if (coinsResponse != null && coinsResponse.status == "success") {
            view.displayCoins(coinsResponse.data.coins)
        } else {
            view.displayError("Failed to load coins")
        }
    }
}
