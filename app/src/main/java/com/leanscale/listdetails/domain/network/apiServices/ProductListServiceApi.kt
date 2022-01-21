package com.leanscale.listdetails.domain.network.apiServices

import com.leanscale.listdetails.domain.models.ProductListResponse


interface ProductListServiceApi {
    suspend fun getProductList() : ProductListResponse?
}