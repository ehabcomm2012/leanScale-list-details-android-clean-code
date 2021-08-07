package com.swensonhe.currencyconverter.features.currencyRatesList

import com.swensonhe.currencyconverter.domain.models.ProductListResponse

sealed class ProductListViewState {
    object  LoadingState : ProductListViewState()
    data class FailureState(val message: String) : ProductListViewState()
    data class SuccessState(val productList: ProductListResponse) : ProductListViewState()
}