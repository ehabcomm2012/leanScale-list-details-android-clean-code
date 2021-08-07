package com.swensonhe.currencyconverter.domain.network.repositories

import com.swensonhe.currencyconverter.domain.models.ProductListResponse

interface ProductListRepoInterface {
    suspend fun getCurrencyRates(accessKey:String) : ProductListResponse?
}