package com.swensonhe.currencyconverter.features.currencyRatesList.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.dubizzle.listdetails.R

class ProductListAdapter(
    private var currencyRates: HashMap<String, Float>,
    val onItemClick: (Pair<String, Float>) -> Unit
) :
    RecyclerView.Adapter<ProductListAdapter.CurrencyRateViewHolder>() {


    var countryKeys = currencyRates.map {
        it.key
    }

    fun updateList(currencyRates: HashMap<String, Float>) {
        this.currencyRates = currencyRates

        countryKeys = this.currencyRates.map {
            it.key
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_row, parent, false)

        return CurrencyRateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyRateViewHolder, position: Int) {
        holder.itemView.setOnClickListener(null)
        val country = countryKeys[position]
        val rate = currencyRates.get(country)
        holder.tvCountry.text = country
        holder.tvRate.text = rate.toString()
        if (country != "EUR") {
            holder.itemView.setOnClickListener {
                onItemClick(Pair(country, rate as Float))
            }
        }

    }

    override fun getItemCount(): Int {
        return currencyRates.size
    }

    class CurrencyRateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eurFlag: AppCompatImageView
        val tvCountry: AppCompatTextView
        val tvRate: AppCompatTextView

        init {
            // Define click listener for the ViewHolder's View.
            eurFlag = view.findViewById(R.id.eurFlag)
            tvCountry = view.findViewById(R.id.tvCountry)
            tvRate = view.findViewById(R.id.tvRate)
        }
    }
}