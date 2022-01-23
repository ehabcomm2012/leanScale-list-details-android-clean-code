package com.leanscale.listdetails.domain.usecase

import com.leanscale.listdetails.domain.models.GamesRequest
import com.leanscale.listdetails.domain.network.repositories.GamesListRepoInterface
import com.leanscale.listdetails.features.gamesList.GamesListViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

 class GetGamesListUseCase @Inject constructor(val gamesListRepoInterface: GamesListRepoInterface) :
    BaseUseCase<GamesRequest, GamesListViewState?>() {


     override fun execute(gamesRequest: GamesRequest?): Flow<GamesListViewState?> {
         return flow {
             emit(GamesListViewState.LoadingState)
             try {
                 val gamesListResponse = gamesListRepoInterface.getGamesList(gamesRequest?.key!!,gamesRequest?.pageSize,gamesRequest.page)

                 if (gamesListResponse?.results!=null)
                     emit(GamesListViewState.SuccessState(gamesListResponse.results,gamesListResponse.count))
                 else
                     emit(GamesListViewState.EmptyState)

             } catch (e: Exception) {
                 e.printStackTrace()
                 emit(GamesListViewState.FailureState("network connection error"))

             }
         }
     }

 }