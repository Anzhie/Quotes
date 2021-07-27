package com.khasanova.quotes.domain.quotes.mappers

import com.khasanova.quotes.QuotesTestData
import com.khasanova.quotes.domain.quotes.mappers.QuotesSymbolsMapper
import kotlin.test.Test
import kotlin.test.assertEquals

object QuotesSymbolsMapperTest {

    @Test
    fun mapSymbols() {
        assertEquals(
            expected = QuotesTestData.mappedQuotesList,
            actual = QuotesSymbolsMapper.map(QuotesTestData.quotesList)
        )
    }

    @Test
    fun mapSymbol() {
        val quote = "AMD"
        assertEquals(
            expected = quote,
            actual = QuotesSymbolsMapper.map(listOf(quote))
        )
    }
}