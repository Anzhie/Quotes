package com.khasanova.quotes.di

import com.khasanova.quotes.source.YahooFinanceApiService
import com.khasanova.quotes.domain.YahooFinanceServiceContract
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

private const val HEADER_HOST = "x-rapidapi-host"
private const val HEADER_HOST_APIDOJO = "apidojo-yahoo-finance-v1.p.rapidapi.com"

@Module
class AppModuleProvides {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(
                Interceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader(HEADER_HOST, HEADER_HOST_APIDOJO)
                        .build()
                    chain.proceed(request)
                }
            )
            .build()

    @Singleton
    @Provides
    fun provideYahooFinanceApiService(okHttpClient: OkHttpClient): YahooFinanceServiceContract =
        YahooFinanceApiService(okHttpClient)
}