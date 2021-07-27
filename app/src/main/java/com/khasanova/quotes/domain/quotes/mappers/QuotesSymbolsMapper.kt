package com.khasanova.quotes.domain.quotes.mappers

object QuotesSymbolsMapper {

    fun map(symbols: List<String>): String = symbols.joinToString(separator = "%2C")
}