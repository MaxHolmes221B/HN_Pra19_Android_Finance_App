package com.example.fina.screen.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.fina.R
import com.example.fina.data.model.Coin
import com.example.fina.databinding.ItemCoinBinding
import com.example.fina.utils.OnItemRecyclerViewClickListener
import com.example.fina.utils.ext.loadImageCircleWithUrl
import com.example.fina.utils.ext.notNull
import com.example.fina.utils.ext.roundOneDecimal
import com.example.fina.utils.ext.roundTwoDecimal

class CoinsAdapter : RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {
    private val coins = mutableListOf<Coin>()
    private var onItemClickListener: OnItemRecyclerViewClickListener<Coin>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewData(coins[position])
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    fun registerItemRecyclerViewClickListener(listener: OnItemRecyclerViewClickListener<Coin>) {
        onItemClickListener = listener
    }

    fun updateData(coins: MutableList<Coin>?) {
        coins.notNull {
            this.coins.clear()
            this.coins.addAll(it)
            notifyDataSetChanged()
        }

    }

    class ViewHolder(
        private val binding: ItemCoinBinding,
        private val itemClickListener: OnItemRecyclerViewClickListener<Coin>?
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var coinData: Coin? = null

        init {
            binding.root.setOnClickListener(this)
        }

        fun bindViewData(coin: Coin) {
            coinData = coin
            binding.coinIcon.loadImageCircleWithUrl(coin.iconUrl)
            binding.coinSymbol.text = coin.symbol
            binding.coinName.text = coin.name
            binding.marketCap.text = coin.marketCap.toDouble().roundOneDecimal()
            binding.coinPrice.text = coin.price.toDouble().roundTwoDecimal()
            val temp = coin.change + "%"
            binding.coinPriceChange.text = temp
            binding.coinPriceChangeImg.setImageDrawable(
                if (coin.change.startsWith("-")) {
                    AppCompatResources.getDrawable(binding.root.context, R.drawable.down)
                } else {
                    AppCompatResources.getDrawable(binding.root.context, R.drawable.up)
                }
            )
            binding.volumeValue.text = coin.volume24h.toDouble().roundOneDecimal()
            binding.volumeTimePeriod.text = coin.rank.toString()
        }

        override fun onClick(view: View?) {
            itemClickListener?.onItemClick(coinData)
        }
    }
}
