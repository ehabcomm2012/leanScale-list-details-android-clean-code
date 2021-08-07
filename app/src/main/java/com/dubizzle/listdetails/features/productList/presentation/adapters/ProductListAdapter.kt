package com.dubizzle.listdetails.features.productList.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.dubizzle.listdetails.R
import com.dubizzle.listdetails.domain.models.Product

class ProductListAdapter(
    private var productList: List<Product>,
    val onItemClick: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductListAdapter.CurrencyRateViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_row, parent, false)

        return CurrencyRateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyRateViewHolder, position: Int) {
        val product = productList[position]
        holder.tvCountry.text = product.name
        holder.tvRate.text = product.price
            holder.itemView.setOnClickListener {
                onItemClick(product)
            }

    }

    override fun getItemCount(): Int {
        return productList.size
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