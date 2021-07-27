package com.khasanova.quotes.entities

class QuotesByIntervalParams(
    val key: String,
    val region: String,
    val symbols: String,
    val initialDelay: Long = 0L,
    val period: Long = 20L
)