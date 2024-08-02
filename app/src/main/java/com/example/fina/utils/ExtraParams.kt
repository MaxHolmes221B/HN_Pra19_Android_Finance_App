package com.example.fina.utils

import com.example.fina.R
class ExtraParams(
    private var referenceCurrencyUuid: String = Constant.USD_UUID,
    private var timePeriodOption: TimePeriodOption = TimePeriodOption(R.string.twentyFourHour, "24h")
) {
    override fun toString(): String {
        return "referenceCurrencyUuid=$referenceCurrencyUuid&timePeriod=${timePeriodOption.value}"
    }

    fun getReferenceCurrencyUuid(): String {
        return referenceCurrencyUuid
    }
    fun getTimePeriodOption(): TimePeriodOption {
        return timePeriodOption
    }
    fun updateReferenceCurrency(referenceCurrencyUuid: String) {
        this.referenceCurrencyUuid = referenceCurrencyUuid
    }
    fun updateTimePeriod(timePeriodOption: TimePeriodOption) {
        this.timePeriodOption = timePeriodOption
    }
}


class OrderProperties(
    private var orderBy: OrderBy = OrderBy(R.string.marketCap, "marketCap"),
    private var orderDirection: OrderDirection = OrderDirection.DESC,
    private var limit: Int = Constant.DEFAULT_LIMIT,
    private var offset: Int = 0,
) {
    fun updateOrderBy(orderBy: OrderBy) {
        this.orderBy = orderBy
    }

    override fun toString(): String {
        return "orderBy=${orderBy.value}&orderDirection=$orderDirection&limit=$limit&offset=$offset"
    }
}

class CurrencyParam(
    private val limit: Int = Constant.DEFAULT_LIMIT,
    private val offset: Int = 0
) {
    override fun toString(): String {
        return "limit=$limit&offset=$offset"
    }
}