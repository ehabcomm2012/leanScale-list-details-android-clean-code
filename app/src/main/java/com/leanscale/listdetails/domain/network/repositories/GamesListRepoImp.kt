package com.leanscale.listdetails.domain.network.repositories

import com.leanscale.listdetails.domain.models.ProductListResponse
import com.leanscale.listdetails.domain.network.apiServices.GamesListServiceApi
import javax.inject.Inject


 class GamesListRepoImp @Inject constructor(val gamesListServiceApi : GamesListServiceApi) : GamesListRepoInterface {
    override suspend fun getGamesList(): ProductListResponse? {
       return gamesListServiceApi.getGamesList()
    }
}