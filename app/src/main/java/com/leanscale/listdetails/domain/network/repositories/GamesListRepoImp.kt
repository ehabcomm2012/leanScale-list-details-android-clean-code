package com.leanscale.listdetails.domain.network.repositories

import com.leanscale.listdetails.domain.models.GamesListResponse
import com.leanscale.listdetails.domain.network.apiServices.GamesListServiceApi
import retrofit2.http.Query
import javax.inject.Inject


 class GamesListRepoImp @Inject constructor(val gamesListServiceApi : GamesListServiceApi) : GamesListRepoInterface {
    override suspend fun getGamesList( key: String,
                                        pageSize: Int,
                                        page: Int): GamesListResponse? {
       return gamesListServiceApi.getGamesList(key ,pageSize,page)
    }
}