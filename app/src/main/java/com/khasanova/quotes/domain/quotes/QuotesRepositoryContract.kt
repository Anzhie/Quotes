package com.khasanova.quotes.domain.quotes

import com.khasanova.quotes.entities.Quote
import io.reactivex.rxjava3.core.Observable

interface QuotesRepositoryContract {

    fun getQuotes(
        key: String,
        quotesList: List<String>? = null,
        chosenRegion: String? = null
    ): Observable<List<Quote>>
}