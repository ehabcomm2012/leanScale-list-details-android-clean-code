package com.leanscale.listdetails.domain.network.apiServices

import com.leanscale.listdetails.domain.models.ProductListResponse


interface GamesListServiceApi {
    suspend fun getGamesList() : ProductListResponse?
}