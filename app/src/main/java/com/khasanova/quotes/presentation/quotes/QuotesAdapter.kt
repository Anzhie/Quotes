package com.khasanova.quotes.presentation.quotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.khasanova.quotes.entities.Quote
import javax.inject.Inject

class QuotesAdapter @Inject constructor(
    asyncDifferConfig: AsyncDifferConfig<Quote>
) : ListAdapter<Quote, QuoteViewHolder>(asyncDifferConfig) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuoteViewHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) =
        holder.bind(getItem(position))
}