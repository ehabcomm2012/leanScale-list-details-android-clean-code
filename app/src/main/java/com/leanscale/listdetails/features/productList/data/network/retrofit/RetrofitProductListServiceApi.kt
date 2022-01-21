package com.leanscale.listdetails.features.productList.data.network.retrofit

import com.leanscale.listdetails.domain.models.ProductListResponse
import retrofit2.http.GET

interface RetrofitProductListServiceApi {
    @GET("default/dynamodb-writer")
     suspend fun getProductList(): ProductListResponse?
}