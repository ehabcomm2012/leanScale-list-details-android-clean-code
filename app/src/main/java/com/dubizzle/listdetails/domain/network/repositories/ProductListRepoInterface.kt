package com.dubizzle.listdetails.domain.network.repositories

import com.dubizzle.listdetails.domain.models.ProductListResponse

interface ProductListRepoInterface {
    suspend fun getCurrencyRates(accessKey:String) : ProductListResponse?
}