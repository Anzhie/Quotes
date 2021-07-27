package com.khasanova.quotes.presentation.quotes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.khasanova.quotes.App
import com.khasanova.quotes.R
import com.khasanova.quotes.databinding.FragmentQuotesBinding
import com.khasanova.quotes.databinding.ViewSettingsBinding
import com.khasanova.quotes.entities.NetworkStatus
import com.khasanova.quotes.entities.Quote
import javax.inject.Inject

class QuotesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @VisibleForTesting
    val viewModel by viewModels<QuotesContract.ViewModel> { viewModelFactory }

    @Inject
    lateinit var asyncDifferConfig: AsyncDifferConfig<Quote>

    @Inject
    lateinit var adapter: QuotesAdapter

    private var quotesBinding: FragmentQuotesBinding? = null

    override fun onAttach(context: Context) {
        (context.applicationContext as? App)?.appComponent?.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quotesBinding = FragmentQuotesBinding.inflate(inflater, container, false)

        return quotesBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        getSettings()
    }

    override fun onDestroyView() {
        quotesBinding = null
        super.onDestroyView()
    }

    private fun setupViews() {
        setupNetworkSate()

        setupRecyclerView()

        setupErrorLayout()
    }

    private fun getSettings() {
        context?.let { context ->
            val settingsViewBinding = ViewSettingsBinding.inflate(LayoutInflater.from(context))

            MaterialAlertDialogBuilder(requireContext())
                .setView(settingsViewBinding.root)
                .setPositiveButton(R.string.ok) { _, _ ->
                    viewModel.setRapidKey(settingsViewBinding.editTextRapidKey.text.toString())
                    viewModel.loadQuotes()
                }
                .setNeutralButton(R.string.cancel) { _, _ -> showError() }
                .setCancelable(false)
                .show()
        }
    }

    private fun setupRecyclerView() {
        quotesBinding?.recyclerViewQuotes?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@QuotesFragment.adapter
        }

        viewModel.quotes.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    private fun setupErrorLayout() {
        quotesBinding?.includedError?.apply {
            buttonSettings.setOnClickListener { getSettings() }
            buttonError.setOnClickListener { viewModel.loadQuotes() }
        }
    }

    private fun setupNetworkSate() {
        viewModel.networkSate.observe(viewLifecycleOwner) {
            when (it) {
                NetworkStatus.LOADING -> {
                    quotesBinding?.apply {
                        progressBarQuotes.isVisible = true
                        recyclerViewQuotes.isGone = true
                        includedError.layoutError.isGone = true
                    }
                }
                NetworkStatus.SUCCESS -> {
                    quotesBinding?.apply {
                        progressBarQuotes.isGone = true
                        recyclerViewQuotes.isVisible = true
                        includedError.layoutError.isGone = true
                    }
                }
                NetworkStatus.ERROR -> {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        quotesBinding?.apply {
            progressBarQuotes.isGone = true
            recyclerViewQuotes.isGone = true
            includedError.layoutError.isVisible = true
        }
    }
}