package com.khasanova.quotes.domain.quotes

import com.khasanova.quotes.domain.YahooFinanceServiceContract
import com.khasanova.quotes.entities.QuotesByIntervalParams
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

open class QuotesApiInteractor @Inject constructor(
    private val financeService: YahooFinanceServiceContract
) : QuotesApiInteractorContract {

    override fun getQuotesByInterval(params: QuotesByIntervalParams) =
        Observable.interval(
            params.initialDelay,
            params.period,
            TimeUnit.SECONDS,
            Schedulers.io()
        )
            .flatMap { financeService.marketApi.getQuotes(params.key, params.region, params.symbols) }
}