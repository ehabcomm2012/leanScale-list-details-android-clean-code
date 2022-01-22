package com.leanscale.listdetails.domain.usecase

import com.leanscale.listdetails.domain.network.repositories.GamesListRepoInterface
import com.leanscale.listdetails.features.gamesList.GamesListViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

 class GetGamesListUseCase @Inject constructor(val gamesListRepoInterface: GamesListRepoInterface) :
    BaseUseCase<String?, GamesListViewState?>() {
    override fun execute(params: String?): Flow<GamesListViewState?> {
        return flow {
            emit(GamesListViewState.LoadingState)
            try {
                val gamesList = gamesListRepoInterface.getGamesList()

                if (gamesList?.results!=null)
                    emit(GamesListViewState.SuccessState(gamesList))
                else
                    emit(GamesListViewState.EmptyState)

            } catch (e: Exception) {
                e.printStackTrace()
                emit(GamesListViewState.FailureState("network connection error"))

            }
        }
    }
}