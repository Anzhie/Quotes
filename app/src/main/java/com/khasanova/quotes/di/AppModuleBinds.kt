package com.khasanova.quotes.di

import androidx.lifecycle.ViewModelProvider
import com.khasanova.quotes.domain.quotes.QuotesApiInteractor
import com.khasanova.quotes.domain.quotes.QuotesApiInteractorContract
import com.khasanova.quotes.domain.quotes.QuotesRepository
import com.khasanova.quotes.domain.quotes.QuotesRepositoryContract
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Singleton
    @Binds
    abstract fun bindQuotesRepository(quotesRepository: QuotesRepository): QuotesRepositoryContract

    @Singleton
    @Binds
    abstract fun bindQuotesApiInteractor(quotesApiInteractorContract: QuotesApiInteractor): QuotesApiInteractorContract
}