package com.khasanova.quotes.presentation.quotes.di

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.AsyncDifferConfig
import com.khasanova.quotes.di.ViewModelKey
import com.khasanova.quotes.entities.Quote
import com.khasanova.quotes.presentation.quotes.QuoteItemCallback
import com.khasanova.quotes.presentation.quotes.QuotesContract
import com.khasanova.quotes.presentation.quotes.QuotesViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class QuotesModule {

    @Provides
    @IntoMap
    @ViewModelKey(QuotesContract.ViewModel::class)
    fun bindQuotesViewModel(quotesViewModel: QuotesViewModel): ViewModel = quotesViewModel

    @Provides
    fun provideAsyncDifferConfig(): AsyncDifferConfig<Quote> =
        AsyncDifferConfig.Builder(QuoteItemCallback()).build()
}