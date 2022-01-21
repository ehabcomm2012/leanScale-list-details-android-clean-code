package com.leanscale.listdetails.domain.usecase

import com.leanscale.listdetails.domain.network.repositories.ProductListRepoInterface
import com.leanscale.listdetails.features.productList.ProductListViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

 class GetProductListUseCase @Inject constructor(val productListRepoInterface: ProductListRepoInterface) :
    BaseUseCase<String?, ProductListViewState?>() {
    override fun execute(params: String?): Flow<ProductListViewState?> {
        return flow {
            emit(ProductListViewState.LoadingState)
            try {
                val productList = productListRepoInterface.getProductList()

                if (productList?.results!=null)
                    emit(ProductListViewState.SuccessState(productList))
                else
                    emit(ProductListViewState.EmptyState)

            } catch (e: Exception) {
                e.printStackTrace()
                emit(ProductListViewState.FailureState("Failed from a network connection error"))

            }
        }
    }
}