package com.khasanova.quotes.presentation.quotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.khasanova.quotes.R
import com.khasanova.quotes.databinding.ItemQuoteBinding
import com.khasanova.quotes.entities.Quote
import com.khasanova.quotes.presentation.roundToTwoDigits

class QuoteViewHolder(
    layoutInflater: LayoutInflater, parent: ViewGroup
) : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_quote, parent, false)) {

    private var binding = ItemQuoteBinding.bind(itemView)

    fun bind(item: Quote) = with(binding) {
        imageViewQuoteChange.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                when {
                    item.regularMarketChange > 0 -> R.drawable.ic_quote_up
                    item.regularMarketChange < 0 -> R.drawable.ic_quote_down
                    else -> R.drawable.ic_qupte_no_change
                }
            )
        )
        textViewQuoteSymbol.text = item.symbol
        textViewQuoteName.text = item.longName
        textViewQuoteLastPrice.text = item.regularMarketPrice.roundToTwoDigits().toString()
        textViewQuoteCurrency.text = item.currency
    }
}