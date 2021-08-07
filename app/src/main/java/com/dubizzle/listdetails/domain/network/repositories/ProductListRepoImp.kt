package com.swensonhe.currencyconverter.domain.network.repositories

import com.swensonhe.currencyconverter.domain.models.ProductListResponse
import com.swensonhe.currencyconverter.domain.network.apiServices.ProductListServiceApi
import javax.inject.Inject


 class ProductListRepoImp @Inject constructor(val productListServiceApi : ProductListServiceApi) : ProductListRepoInterface {
    override suspend fun getCurrencyRates(accessKey: String): ProductListResponse? {
       return productListServiceApi.getCurrencyRates(accessKey)
    }
}