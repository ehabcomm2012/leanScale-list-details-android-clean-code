package com.leanscale.listdetails.domain.network.apiServices

import com.leanscale.listdetails.domain.models.GamesListResponse


interface GamesListServiceApi {
    suspend fun getGamesList(key: String,
                             pageSize: Int,
                             page: Int) : GamesListResponse?
}