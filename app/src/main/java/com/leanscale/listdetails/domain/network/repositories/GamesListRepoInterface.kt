package com.leanscale.listdetails.domain.network.repositories

import com.leanscale.listdetails.domain.models.ProductListResponse


interface GamesListRepoInterface {
    suspend fun getGamesList() : ProductListResponse?
}