package com.khasanova.quotes.domain.quotes

import com.khasanova.quotes.QuotesTestData
import com.khasanova.quotes.TestSchedulerExtension
import com.khasanova.quotes.entities.QuotesByIntervalParams
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.refEq
import org.mockito.kotlin.verify
import kotlin.test.Test

@ExtendWith(value = [TestSchedulerExtension::class])
class QuotesRepositoryTest {

    private val quotesApiInteractor = mock<QuotesApiInteractorContract> {
        on { getQuotesByInterval(any()) } doReturn
            Observable.just(QuotesTestData.quotesResponseModel)
    }

    private val quotesRepository = QuotesRepository(quotesApiInteractor)

    @Test
    fun getQuotesWithKey() {
        quotesRepository.getQuotes(QuotesTestData.key)

        verify(quotesApiInteractor).getQuotesByInterval(refEq(QuotesTestData.quotesByIntervalParams))
    }

    @Test
    fun getQuotesWithKeyAndQuotes() {
        quotesRepository.getQuotes(QuotesTestData.key, QuotesTestData.quotesList)

        verify(quotesApiInteractor).getQuotesByInterval(
            refEq(
                QuotesByIntervalParams(
                    QuotesTestData.key,
                    QuotesTestData.defaultRegionTest,
                    QuotesTestData.mappedQuotesList
                )
            )
        )
    }

    @Test
    fun getQuotesWithKeyAndQuotesAndRegion() {
        quotesRepository.getQuotes(QuotesTestData.key, QuotesTestData.quotesList, QuotesTestData.defaultRegionTest)

        verify(quotesApiInteractor).getQuotesByInterval(
            refEq(
                QuotesByIntervalParams(
                    QuotesTestData.key,
                    QuotesTestData.defaultRegionTest,
                    QuotesTestData.mappedQuotesList
                )
            )
        )
    }

    @Test
    fun getQuotesWithKeyAndEmptyQuotes() {
        quotesRepository.getQuotes(QuotesTestData.key, listOf<String>())

        verify(quotesApiInteractor).getQuotesByInterval(refEq(QuotesTestData.quotesByIntervalParams))
    }

    @Test
    fun getQuotesWithKeyAndQuotesAndBlankRegion() {
        quotesRepository.getQuotes(QuotesTestData.key, QuotesTestData.quotesList, "")

        verify(quotesApiInteractor).getQuotesByInterval(
            refEq(
                QuotesByIntervalParams(
                    QuotesTestData.key,
                    QuotesTestData.defaultRegionTest,
                    QuotesTestData.mappedQuotesList
                )
            )
        )
    }
}