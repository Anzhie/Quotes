package com.khasanova.quotes.entities

data class Quote(
    val symbol: String,
    val longName: String,
    val regularMarketPrice: Double,
    val regularMarketChange: Double,
    val currency: String
)