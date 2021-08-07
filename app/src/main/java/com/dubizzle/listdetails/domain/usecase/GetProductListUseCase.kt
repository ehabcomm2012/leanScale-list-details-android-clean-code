package com.swensonhe.currencyconverter.domain.usecase

import com.swensonhe.currencyconverter.domain.network.repositories.ProductListRepoInterface
import com.swensonhe.currencyconverter.features.currencyRatesList.ProductListViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

 class GetProductListUseCase @Inject constructor(val productListRepoInterface: ProductListRepoInterface) :
    BaseUseCase<String, ProductListViewState?>() {
    override fun execute(accessKey: String): Flow<ProductListViewState?> {
        return flow {
            emit(ProductListViewState.LoadingState)
            try {
                val ratesList = productListRepoInterface.getCurrencyRates(accessKey)

                if (ratesList != null && ratesList.success)
                    emit(ProductListViewState.SuccessState(ratesList))
                else
                    emit(ProductListViewState.FailureState("Failed from the server error"))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ProductListViewState.FailureState("Failed from a network connection error"))

            }
        }
    }
}