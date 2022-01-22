package com.leanscale.listdetails.features.gamesList

import com.leanscale.listdetails.domain.models.ProductListResponse

sealed class GamesListViewState {
    object  LoadingState : GamesListViewState()
    data class FailureState(val message: String) : GamesListViewState()
    object  EmptyState : GamesListViewState()
    data class SuccessState(val productList: ProductListResponse) : GamesListViewState()
}