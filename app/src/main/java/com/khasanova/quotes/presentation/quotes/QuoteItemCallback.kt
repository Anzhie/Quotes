package com.khasanova.quotes.presentation.quotes

import androidx.recyclerview.widget.DiffUtil
import com.khasanova.quotes.entities.Quote

class QuoteItemCallback : DiffUtil.ItemCallback<Quote>() {

    override fun areItemsTheSame(oldItem: Quote, newItem: Quote) =
        oldItem.symbol == oldItem.symbol

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote) =
        oldItem.regularMarketPrice == oldItem.regularMarketPrice
}