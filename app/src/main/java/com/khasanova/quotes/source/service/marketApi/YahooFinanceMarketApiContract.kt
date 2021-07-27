package com.khasanova.quotes.source.service.marketApi

import com.khasanova.quotes.entities.QuotesResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val HEADER_X_RAPID_KEY= "x-rapidapi-key"
private const val MARKET_ENDPOINT = "market/"
private const val QUOTES_ENDPOINT = "v2/get-quotes"
private const val QUERY_REGION = "region"
private const val QUERY_SYMBOLS = "symbols"

interface YahooFinanceMarketApiContract {

    @GET(MARKET_ENDPOINT + QUOTES_ENDPOINT)
    fun getQuotes(
        @Header(HEADER_X_RAPID_KEY) key: String,
        @Query(QUERY_REGION) region: String,
        @Query(QUERY_SYMBOLS) symbols: String
    ): Observable<QuotesResponseModel>
}