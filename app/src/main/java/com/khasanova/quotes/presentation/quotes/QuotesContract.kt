package com.khasanova.quotes.presentation.quotes

import androidx.lifecycle.LiveData
import com.khasanova.quotes.entities.NetworkStatus
import com.khasanova.quotes.entities.Quote

interface QuotesContract {

    abstract class ViewModel : androidx.lifecycle.ViewModel() {

        abstract val rapidKey: LiveData<String>

        abstract val networkSate: LiveData<NetworkStatus>

        abstract val quotes: LiveData<List<Quote>>

        abstract fun setRapidKey(yahooFinanceKey: String)

        abstract fun loadQuotes()
    }
}