package com.khasanova.quotes.source

import com.khasanova.quotes.domain.YahooFinanceServiceContract
import com.khasanova.quotes.source.service.marketApi.YahooFinanceMarketApiContract
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

private const val BASE_URL = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/"

class YahooFinanceApiService @Inject constructor(
    okHttpClient: OkHttpClient
) : YahooFinanceServiceContract {

    private val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override val marketApi: YahooFinanceMarketApiContract
        get() = service.create(YahooFinanceMarketApiContract::class.java)
}