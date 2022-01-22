package com.leanscale.listdetails.features.gamesList.data.network.retrofit


import com.leanscale.listdetails.domain.models.ProductListResponse
import com.leanscale.listdetails.domain.network.apiServices.GamesListServiceApi
import javax.inject.Inject


class RetrofitGamesListServiceApiImp @Inject constructor(val retrofitGamesListServiceApi: RetrofitGamesListServiceApi) :
    GamesListServiceApi {
    override suspend fun getGamesList(): ProductListResponse? {
        return retrofitGamesListServiceApi.getGamesList()
    }
}