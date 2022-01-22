package com.leanscale.listdetails.features.gamesList.data.network.retrofit


import com.leanscale.listdetails.domain.models.GamesListResponse
import com.leanscale.listdetails.domain.network.apiServices.GamesListServiceApi
import javax.inject.Inject


class RetrofitGamesListServiceApiImp @Inject constructor(val retrofitGamesListServiceApi: RetrofitGamesListServiceApi) :
    GamesListServiceApi {
    override suspend fun getGamesList(key: String,
                                      pageSize: Int,
                                      page: Int): GamesListResponse? {
        return retrofitGamesListServiceApi.getGamesList(key,
            pageSize,
            page)
    }
}