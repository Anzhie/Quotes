package com.khasanova.quotes.domain

import com.khasanova.quotes.source.service.marketApi.YahooFinanceMarketApiContract

interface YahooFinanceServiceContract {

    val marketApi: YahooFinanceMarketApiContract
}