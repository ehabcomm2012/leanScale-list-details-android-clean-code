package com.dubizzle.listdetails.features.productList.data.network.retrofit

import com.dubizzle.listdetails.domain.models.ProductListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitProductListServiceApi {
    @GET("latest")
     suspend fun getCurrencyRates(@Query("access_key") accessKey :String): ProductListResponse?
}