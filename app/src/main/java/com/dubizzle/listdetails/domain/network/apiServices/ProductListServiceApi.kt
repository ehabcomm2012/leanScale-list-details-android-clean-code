package com.swensonhe.currencyconverter.domain.network.apiServices

import com.swensonhe.currencyconverter.domain.models.ProductListResponse

interface ProductListServiceApi {
    suspend fun getCurrencyRates(accessKey:String) : ProductListResponse?
}