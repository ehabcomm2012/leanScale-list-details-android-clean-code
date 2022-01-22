package com.leanscale.listdetails.features.gamesList.data.network.retrofit

import com.leanscale.listdetails.domain.models.GamesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitGamesListServiceApi {
    @GET("games")
     suspend fun getGamesList(@Query("key") key: String ,
                              @Query("page_size") pageSize: Int,
                              @Query("page") page: Int): GamesListResponse?
}