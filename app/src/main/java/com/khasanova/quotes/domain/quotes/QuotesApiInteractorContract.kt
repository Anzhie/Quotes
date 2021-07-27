package com.khasanova.quotes.domain.quotes

import com.khasanova.quotes.entities.QuotesByIntervalParams
import com.khasanova.quotes.entities.QuotesResponseModel
import io.reactivex.rxjava3.core.Observable

interface QuotesApiInteractorContract {

    fun getQuotesByInterval(params: QuotesByIntervalParams): Observable<QuotesResponseModel>
}