package com.leanscale.listdetails.features.gamesList.data.network.retrofit

import com.leanscale.listdetails.domain.models.ProductListResponse
import retrofit2.http.GET

interface RetrofitGamesListServiceApi {
    @GET("default/dynamodb-writer")
     suspend fun getGamesList(): ProductListResponse?
}