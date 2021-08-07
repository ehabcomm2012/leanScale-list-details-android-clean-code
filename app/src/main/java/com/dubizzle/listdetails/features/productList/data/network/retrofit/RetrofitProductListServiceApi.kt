package com.swensonhe.currencyconverter.features.currencyRatesList.data.network.retrofit

import com.swensonhe.currencyconverter.domain.models.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitProductListServiceApi {
    @GET("latest")
     suspend fun getCurrencyRates(@Query("access_key") accessKey :String): ProductListResponse?
}