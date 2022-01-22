package com.leanscale.listdetails.domain.network.repositories

import com.leanscale.listdetails.domain.models.GamesListResponse


interface GamesListRepoInterface {
    suspend fun getGamesList( key: String,
                              pageSize: Int,
                              page: Int) : GamesListResponse?
}