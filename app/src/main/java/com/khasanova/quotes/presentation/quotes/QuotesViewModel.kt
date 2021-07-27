package com.khasanova.quotes.presentation.quotes

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.khasanova.quotes.entities.NetworkStatus
import com.khasanova.quotes.entities.Quote
import com.khasanova.quotes.domain.quotes.QuotesRepositoryContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val quotesRepository: QuotesRepositoryContract
) : QuotesContract.ViewModel() {

    override val rapidKey: MutableLiveData<String> = MutableLiveData()

    override val networkSate: MutableLiveData<NetworkStatus> = MutableLiveData()

    override val quotes: MutableLiveData<List<Quote>> = MutableLiveData()

    private var disposable: Disposable? = null

    override fun loadQuotes() =
        rapidKey.value?.let { yahooFinanceKeyValue ->
            if (yahooFinanceKeyValue.isNotBlank()) {
                getQuotes(yahooFinanceKeyValue)
            } else {
                showError()
            }
        } ?: showError()

    override fun setRapidKey(yahooFinanceKey: String) {
        this.rapidKey.value = yahooFinanceKey
    }

    @VisibleForTesting
    public override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    private fun getQuotes(key: String) {
        disposable?.dispose()

        disposable = quotesRepository.getQuotes(key)
            .doOnSubscribe { networkSate.value = NetworkStatus.LOADING }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { quotesList ->
                    networkSate.value = NetworkStatus.SUCCESS
                    quotes.value = quotesList.sortedByDescending { it.regularMarketPrice }
                },
                { showError() }
            )
    }

    private fun showError() {
        networkSate.value = NetworkStatus.ERROR
    }
}