package com.dubizzle.listdetails.domain.network.apiServices

import com.dubizzle.listdetails.domain.models.ProductListResponse

interface ProductListServiceApi {
    suspend fun getProductList() : ProductListResponse?
}