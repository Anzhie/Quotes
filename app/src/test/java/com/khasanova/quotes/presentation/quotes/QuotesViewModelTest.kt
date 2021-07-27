package com.khasanova.quotes.presentation.quotes

import com.khasanova.quotes.InstantExecutorExtension
import com.khasanova.quotes.QuotesTestData
import com.khasanova.quotes.TestSchedulerExtension
import com.khasanova.quotes.domain.quotes.QuotesRepositoryContract
import com.khasanova.quotes.entities.NetworkStatus
import com.khasanova.quotes.presentation.getOrAwaitValue
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(value = [TestSchedulerExtension::class, InstantExecutorExtension::class])
class QuotesViewModelTest {

    private val quotesRepository = mock<QuotesRepositoryContract> {
        on { getQuotes(any(), isNull(), isNull()) } doReturn
            Observable.just(QuotesTestData.quotesResponseModel.quoteResponse.result)
    }

    private lateinit var quotesViewModel: QuotesViewModel

    @BeforeEach
    fun setup() {
        quotesViewModel = QuotesViewModel(quotesRepository)
    }

    @AfterEach
    fun tearDown() {
        quotesViewModel.onCleared()
    }

    @Test
    fun networkStateErrorWhenLoadQuotesNoKey() {
        quotesViewModel.loadQuotes()

        assertEquals(NetworkStatus.ERROR, quotesViewModel.networkSate.getOrAwaitValue())
    }

    @Test
    fun networkStateErrorWhenLoadQuotesBlankKey() {
        quotesViewModel.rapidKey.value = ""

        quotesViewModel.loadQuotes()

        assertEquals(NetworkStatus.ERROR, quotesViewModel.networkSate.getOrAwaitValue())
    }

    @Test
    fun networkStateSuccessWhenLoadQuotesWithKey() {
        quotesViewModel.rapidKey.value = QuotesTestData.key

        quotesViewModel.loadQuotes()

        assertEquals(NetworkStatus.SUCCESS, quotesViewModel.networkSate.getOrAwaitValue())
    }

    @Test
    fun networkStateErrorWhenLoadQuotesError() {
        whenever(quotesRepository.getQuotes(any(), isNull(), isNull())).thenReturn(Observable.error(RuntimeException()))
        quotesViewModel.rapidKey.value = QuotesTestData.key

        quotesViewModel.loadQuotes()

        assertEquals(NetworkStatus.ERROR, quotesViewModel.networkSate.getOrAwaitValue())
    }

    @Test
    fun quotesWhenLoadQuotesWithKey() {
        quotesViewModel.rapidKey.value = QuotesTestData.key

        quotesViewModel.loadQuotes()

        assertEquals(QuotesTestData.sortedQuotesList, quotesViewModel.quotes.getOrAwaitValue())
    }

    @Test
    fun setKey() {
        quotesViewModel.setRapidKey(QuotesTestData.key)

        assertEquals(QuotesTestData.key, quotesViewModel.rapidKey.getOrAwaitValue())
    }
}