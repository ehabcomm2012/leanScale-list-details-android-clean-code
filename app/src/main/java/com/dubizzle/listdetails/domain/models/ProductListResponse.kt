package com.dubizzle.listdetails.domain.models

import java.io.Serializable

data class ProductListResponse(
    val results: List<Product>?
)

data class Product(
    val created_at: String?,
    val price: String?,
    val name: String?,
    val uid: String?,
    val image_ids: List<String>?,
    val image_urls_thumbnails: List<String>?
) : Serializable

