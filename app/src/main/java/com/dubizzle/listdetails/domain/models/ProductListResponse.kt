package com.dubizzle.listdetails.domain.models

data class ProductListResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: HashMap<String, Float>?
)