package com.dubizzle.listdetails.features.productList

import com.dubizzle.listdetails.domain.models.ProductListResponse

sealed class ProductListViewState {
    object  LoadingState : ProductListViewState()
    data class FailureState(val message: String) : ProductListViewState()
    object  EmptyState : ProductListViewState()
    data class SuccessState(val productList: ProductListResponse) : ProductListViewState()
}