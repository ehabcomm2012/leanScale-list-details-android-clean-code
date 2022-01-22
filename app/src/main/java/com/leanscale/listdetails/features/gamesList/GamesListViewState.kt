package com.leanscale.listdetails.features.gamesList

import com.leanscale.listdetails.domain.models.GamesListResponse

sealed class GamesListViewState {
    object  LoadingState : GamesListViewState()
    data class FailureState(val message: String) : GamesListViewState()
    object  EmptyState : GamesListViewState()
    data class SuccessState(val gamesList: GamesListResponse) : GamesListViewState()
}