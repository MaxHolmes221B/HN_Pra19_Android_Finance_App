package com.example.fina.screen.home

import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fina.R
import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.data.model.OrderBy
import com.example.fina.data.model.TimePeriod
import com.example.fina.data.repository.CoinRepository
import com.example.fina.data.repository.source.local.CoinLocalDataSource
import com.example.fina.data.repository.source.remote.CoinRemoteDataSource
import com.example.fina.databinding.FragmentHomeBinding
//import com.example.fina.screen.detail.CoinDetailFragment
import com.example.fina.screen.home.adapter.CoinsAdapter
import com.example.fina.screen.home.adapter.CurrencyAdapter
import com.example.fina.screen.home.adapter.OrderByAdapter
import com.example.fina.screen.home.adapter.TimePeriodAdapter
import com.example.fina.utils.OnItemRecyclerViewClickListener
import com.example.fina.utils.base.BaseFragment
import com.example.fina.utils.ext.round
import com.example.fina.utils.ext.roundOneDecimal
import com.example.fina.utils.ext.roundTwoDecimal
//import com.example.fina.utils.ext.addFragment
import java.lang.Exception

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(),
    HomeContract.View,
    OnItemRecyclerViewClickListener<Coin> {

    private lateinit var mHomePresenter: HomePresenter
    private val mCoinAdapter: CoinsAdapter by lazy { CoinsAdapter() }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun initView() {
        viewBinding.recyclerViewCoin.apply {
            // Gán LayoutManager cho RecyclerView
            layoutManager = LinearLayoutManager(context)
            adapter = mCoinAdapter
        }
        mCoinAdapter.registerItemRecyclerViewClickListener(this)
        setupSpinners()
        setupBottomNavigation()
    }

    override fun initData() {
        mHomePresenter = HomePresenter(
            CoinRepository.getInstance(
                CoinRemoteDataSource.getInstance(),
                CoinLocalDataSource.getInstance()
            )
        )
        mHomePresenter.setView(this)
        mHomePresenter.getCoins()
        mHomePresenter.getCoinStats()
    }

    override fun onGetCoinsSuccess(coins: MutableList<Coin>) {
        mCoinAdapter.updateData(coins)
    }

    override fun onGetCoinStatsSuccess(coinStats: CoinStats) {
        viewBinding.tvTotalCoinsValue.text = coinStats.totalCoins.round()
        viewBinding.tvTotalMarketplaceValue.text = coinStats.totalMarkets.round()
        viewBinding.tvTotalMarketCapValue.text =
            coinStats.totalMarketCap.toDouble().roundOneDecimal()
        viewBinding.tvVol24hValue.text = coinStats.total24hVolume.toDouble().roundOneDecimal()
    }

    private fun setupSpinners() {
        val currencies = arrayOf("USD", "EUR", "JPY", "GBP")
        val currencyAdapter = CurrencyAdapter(requireContext(), currencies)
        viewBinding.spinnerCurrency.adapter = currencyAdapter

        val timePeriods = TimePeriod.values()
        val timePeriodAdapter = TimePeriodAdapter(requireContext(), timePeriods)
        viewBinding.spinnerTimePeriod.adapter = timePeriodAdapter

        val orderByOptions = OrderBy.values()
        val orderByAdapter = OrderByAdapter(requireContext(), orderByOptions)
        viewBinding.spinnerOrderBy.adapter = orderByAdapter
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(item: Coin?) {
//        addFragment(R.id.layoutContainer, CoinDetailFragment.newInstance(item), true)
    }

    private fun setupBottomNavigation() {
        viewBinding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.favourite -> {
                    Toast.makeText(context, "Favourite", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.search -> {
                    Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.settings -> {
                    Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }


    companion object {
        fun newInstance() = HomeFragment()
    }
}
