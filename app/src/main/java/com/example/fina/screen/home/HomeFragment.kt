package com.example.fina.screen.home

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fina.R
import com.example.fina.data.model.Coin
import com.example.fina.data.model.CoinStats
import com.example.fina.data.model.Currency
import com.example.fina.data.repository.CoinRepository
import com.example.fina.data.repository.source.local.CoinLocalDataSource
import com.example.fina.data.repository.source.remote.CoinRemoteDataSource
import com.example.fina.databinding.FragmentHomeBinding
import com.example.fina.screen.home.adapter.CoinsAdapter
import com.example.fina.screen.home.adapter.CurrencyAdapter
import com.example.fina.screen.home.adapter.OrderByAdapter
import com.example.fina.screen.home.adapter.TimePeriodAdapter
import com.example.fina.utils.Constant
import com.example.fina.utils.ExtraParams
import com.example.fina.utils.OnItemRecyclerViewClickListener
import com.example.fina.utils.OrderBy
import com.example.fina.utils.OrderDirection
import com.example.fina.utils.OrderProperties
import com.example.fina.utils.TimePeriod
import com.example.fina.utils.TimePeriodOption
import com.example.fina.utils.base.BaseFragment
import com.example.fina.utils.ext.round
import com.example.fina.utils.ext.roundOneDecimal
import java.lang.Exception

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(),
    HomeContract.View,
    OnItemRecyclerViewClickListener<Coin> {

    private lateinit var mHomePresenter: HomePresenter
    private val mCoinAdapter: CoinsAdapter by lazy { CoinsAdapter() }
    private val mCurrencyAdapter: CurrencyAdapter by lazy { CurrencyAdapter(requireContext(), arrayListOf()) }
    private var check: Boolean = true
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun initView() {
        viewBinding.recyclerViewCoin.apply {
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
        mHomePresenter.getCurrencies()
        mHomePresenter.getCoins()
        this.check = true
        mHomePresenter.getCoinStats()
    }

    override fun onGetCoinsSuccess(coins: MutableList<Coin>) {

        mCoinAdapter.updateData(coins)
    }

    override fun onGetCurrenciesSuccess(currencies: MutableList<Currency>) {
        val listCurrencies = ArrayList(currencies)
        mCurrencyAdapter.updateData(listCurrencies)
    }

    override fun onGetCoinStatsSuccess(coinStats: CoinStats) {
        viewBinding.tvTotalCoinsValue.text = coinStats.totalCoins.round()
        viewBinding.tvTotalMarketplaceValue.text = coinStats.totalMarkets.round()
        viewBinding.tvTotalMarketCapValue.text = coinStats.totalMarketCap.toDouble().roundOneDecimal()
        viewBinding.tvVol24hValue.text = coinStats.total24hVolume.toDouble().roundOneDecimal()
    }

    private fun setupSpinners() {
        println("Da goi setup spinner")
        viewBinding.spinnerCurrency.adapter = mCurrencyAdapter
        if(check) {
            viewBinding.spinnerCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selectedCurrency = p0?.getItemAtPosition(p2) as Currency
                        Toast.makeText(
                            requireContext(),
                            "Selected Currency: ${selectedCurrency.symbol}",
                            Toast.LENGTH_SHORT
                        ).show()
                        println("Goi currency")
                        mHomePresenter.updateParamsWithCurrency(selectedCurrency)

                }


                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        val listTimePeriodOption = arrayListOf(
            TimePeriodOption(R.string.oneHour, "1h"),
            TimePeriodOption(R.string.threeHour, "3h"),
            TimePeriodOption(R.string.twelveHour, "12h"),
            TimePeriodOption(R.string.twentyFourHour, "24h"),
            TimePeriodOption(R.string.sevenDays, "7d"),
            TimePeriodOption(R.string.thirtyDays, "30d")
        )
        val timePeriodAdapter = TimePeriodAdapter(requireContext(), listTimePeriodOption)
        viewBinding.spinnerTimePeriod.adapter = timePeriodAdapter
        val defaultPosition = listTimePeriodOption.indexOfFirst { it.value == "24h" }
        viewBinding.spinnerTimePeriod.setSelection(defaultPosition)
        if(check) {
            viewBinding.spinnerTimePeriod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selectedTimePeriod = p0?.getItemAtPosition(p2) as TimePeriodOption
                        Toast.makeText(
                            requireContext(),
                            "Selected Time Period: ${selectedTimePeriod.getName(requireContext())}",
                            Toast.LENGTH_SHORT
                        ).show()
                        mHomePresenter.updateParamsWithTimePeriod(selectedTimePeriod)
                        println("Goi time period")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        val listOrder = arrayListOf(
            OrderBy(R.string.marketCap,"marketCap"),
            OrderBy(R.string.price, "price"),
            OrderBy(R.string.volume24h, "24hVolume"),
            OrderBy(R.string.change, "change"),
            OrderBy(R.string.listedAt, "listedAt")
        )
        val orderByAdapter = OrderByAdapter(requireContext(), listOrder)
        viewBinding.spinnerOrderBy.adapter = orderByAdapter

        if(check) {
            viewBinding.spinnerOrderBy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    val selectedOrderBy = p0?.getItemAtPosition(p2) as OrderBy
                    Toast.makeText(
                        requireContext(),
                        "Selected Order By: ${selectedOrderBy.getName(requireContext())}",
                        Toast.LENGTH_SHORT
                    ).show()

                    val newOrderProperties = OrderProperties(
                        orderBy = OrderBy(selectedOrderBy.nameResId, selectedOrderBy.value),
                        orderDirection = OrderDirection.DESC,
                        limit = Constant.DEFAULT_LIMIT,
                        offset = 0
                    )

                    println("Goi order by")
                    mHomePresenter.updateOrderProperties(newOrderProperties)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

//        check = true
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
