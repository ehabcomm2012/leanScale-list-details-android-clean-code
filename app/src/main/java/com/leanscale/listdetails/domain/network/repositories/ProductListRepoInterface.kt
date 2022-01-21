package com.leanscale.listdetails.domain.network.repositories

import com.leanscale.listdetails.domain.models.ProductListResponse


interface ProductListRepoInterface {
    suspend fun getProductList() : ProductListResponse?
}