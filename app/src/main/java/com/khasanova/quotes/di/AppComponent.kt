package com.khasanova.quotes.di

import com.khasanova.quotes.App
import com.khasanova.quotes.presentation.quotes.di.QuotesModule
import com.khasanova.quotes.presentation.quotes.QuotesFragment
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModuleProvides::class,
        AppModuleBinds::class,
        QuotesModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {

        fun create(): AppComponent
    }

    fun inject(quotesFragment: QuotesFragment)
}