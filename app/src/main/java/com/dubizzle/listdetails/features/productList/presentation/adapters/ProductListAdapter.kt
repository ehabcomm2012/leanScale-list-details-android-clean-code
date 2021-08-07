package com.dubizzle.listdetails.features.productList.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
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

        Glide
            .with(holder.itemView.context)
            .load(product.image_urls_thumbnails?.get(0))
            .centerCrop()
            //.placeholder(R.drawable.loading_spinner)
            .into(holder.iv_product)
        holder.tv_title.text = product.name
        holder.tv_shortDesc.text = product.created_at
        holder.tv_price.text = product.price

        holder.itemView.setOnClickListener {
                onItemClick(product)
            }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class CurrencyRateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv_product: AppCompatImageView
        val tv_title: AppCompatTextView
        val tv_shortDesc: AppCompatTextView
        val tv_rating: AppCompatTextView
        val tv_price: AppCompatTextView

        init {
            // Define click listener for the ViewHolder's View.
            iv_product = view.findViewById(R.id.iv_product)
            tv_title = view.findViewById(R.id.tv_title)
            tv_shortDesc = view.findViewById(R.id.tv_shortDesc)
            tv_rating = view.findViewById(R.id.tv_rating)
            tv_price = view.findViewById(R.id.tv_price)
        }
    }
}