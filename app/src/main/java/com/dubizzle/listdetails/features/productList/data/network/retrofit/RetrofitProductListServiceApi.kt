package com.dubizzle.listdetails.features.productList.data.network.retrofit

import com.dubizzle.listdetails.domain.models.ProductListResponse
import retrofit2.http.GET

interface RetrofitProductListServiceApi {
    @GET("default/dynamodb-writer")
     suspend fun getProductList(): ProductListResponse?
}