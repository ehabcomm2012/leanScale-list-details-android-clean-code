package com.leanscale.listdetails.features.productList

import com.leanscale.listdetails.domain.models.ProductListResponse

sealed class ProductListViewState {
    object  LoadingState : ProductListViewState()
    data class FailureState(val message: String) : ProductListViewState()
    object  EmptyState : ProductListViewState()
    data class SuccessState(val productList: ProductListResponse) : ProductListViewState()
}