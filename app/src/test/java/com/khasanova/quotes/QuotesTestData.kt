package com.khasanova.quotes

import com.khasanova.quotes.entities.Quote
import com.khasanova.quotes.entities.QuotesByIntervalParams
import com.khasanova.quotes.entities.QuotesResponse
import com.khasanova.quotes.entities.QuotesResponseModel

object QuotesTestData {

    val quotesList = listOf("AMD", "TSLA", "GOOGL")

    val mappedQuotesList = "AMD%2CTSLA%2CGOOGL"

    val key = "key"

    val quotesResponseModel = QuotesResponseModel(
        QuotesResponse(
            listOf(
                Quote(
                    symbol = "AMD",
                    longName = "Advanced Micro Devices, Inc.",
                    regularMarketPrice = 81.58,
                    regularMarketChange = 1.300003,
                    currency = "USD"
                ),
                Quote(
                    symbol = "TSLA",
                    longName = "Tesla, Inc.",
                    regularMarketPrice = 599.05,
                    regularMarketChange = 26.20996,
                    currency = "USD"
                ),
                Quote(
                    symbol = "GOOGL",
                    longName = "Alphabet Inc.",
                    regularMarketPrice = 2393.57,
                    regularMarketChange = 45.98999,
                    currency = "USD"
                )
            )
        )
    )

    val sortedQuotesList = listOf(
        Quote(
            symbol = "GOOGL",
            longName = "Alphabet Inc.",
            regularMarketPrice = 2393.57,
            regularMarketChange = 45.98999,
            currency = "USD"
        ),
        Quote(
            symbol = "TSLA",
            longName = "Tesla, Inc.",
            regularMarketPrice = 599.05,
            regularMarketChange = 26.20996,
            currency = "USD"
        ),
        Quote(
            symbol = "AMD",
            longName = "Advanced Micro Devices, Inc.",
            regularMarketPrice = 81.58,
            regularMarketChange = 1.300003,
            currency = "USD"
        )
    )

    val defaultQuotesListTest =
        "AMD%2CTSLA%2CGOOGL%2CFB%2CPYPL%2CNVDA%2CMSFT%2CAMZN%2CAAPL%2CV%2CMA%2CNFLX%2CONEQ%2CORLY%2CMKTX"

    val defaultRegionTest = "US"

    val quotesByIntervalParams = QuotesByIntervalParams(key, defaultRegionTest, defaultQuotesListTest)
}