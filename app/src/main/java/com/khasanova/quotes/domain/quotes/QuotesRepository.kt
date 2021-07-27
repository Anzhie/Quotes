package com.khasanova.quotes.domain.quotes

import com.khasanova.quotes.entities.Quote
import com.khasanova.quotes.domain.quotes.mappers.QuotesSymbolsMapper
import com.khasanova.quotes.entities.QuotesByIntervalParams
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class QuotesRepository @Inject constructor(
    private val quotesInteractor: QuotesApiInteractorContract
) : QuotesRepositoryContract {

    private val defaultQuotesList = listOf("AMD", "TSLA", "GOOGL", "FB", "PYPL", "NVDA", "MSFT", "AMZN", "AAPL", "V", "MA", "NFLX", "ONEQ", "ORLY", "MKTX")
    private val defaultRegion = "US"

    override fun getQuotes(key: String, quotesList: List<String>?, chosenRegion: String?): Observable<List<Quote>> {
        val quotes = if (quotesList.isNullOrEmpty()) defaultQuotesList else quotesList
        val region = if (chosenRegion.isNullOrBlank()) defaultRegion else chosenRegion

        return quotesInteractor.getQuotesByInterval(QuotesByIntervalParams(key, region, QuotesSymbolsMapper.map(quotes)))
            .map { it.quoteResponse.result }
    }
}